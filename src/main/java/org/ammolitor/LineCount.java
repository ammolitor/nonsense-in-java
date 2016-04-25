package org.ammolitor;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

public class LineCount extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
        System.exit(ToolRunner.run(new Configuration(), new LineCount(), args));
    }

    @Override
    public int run(String[] strings) throws Exception {
        JobConf conf = new JobConf(getConf());
        conf.setJarByClass(LineCount.class);
        conf.setJobName("LineCount");
        conf.setOutputKeyClass(BooleanWritable.class);
        conf.setOutputValueClass(LongWritable.class);
        conf.setMapperClass(Map.class);
        conf.setCombinerClass(Combine.class);
        conf.setReducerClass(Reduce.class);
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);
        conf.setNumReduceTasks(1); //do all the reduction in a single class
        FileInputFormat.setInputPaths(conf, new Path(strings[0]));
        FileOutputFormat.setOutputPath(conf, new Path(strings[1]));
        long start = System.nanoTime();
        RunningJob runningJob = JobClient.runJob(conf);
        float lastMapProgress = 0f;
        float lastReduceProgress = 0f;
        float threshold = 0.1f;
        while (!runningJob.isComplete()) {
            //sleep for 1 second
            Thread.sleep(1000);
            float mapProgress = runningJob.mapProgress();
            float reduceProgress = runningJob.reduceProgress();
            boolean printed = false;
            if ((mapProgress - lastMapProgress) > threshold) {
                printed = true;
                System.out.printf("mapProgress: %f, reduceProgress: %f%n", mapProgress, reduceProgress);
                lastMapProgress = mapProgress;
            }

            if ((reduceProgress - lastReduceProgress) > threshold) {
                if (!printed)
                    System.out.printf("mapProgress: %f, reduceProgress: %f%n", mapProgress, reduceProgress);
                lastReduceProgress = reduceProgress;
            }
        }
        long end = System.nanoTime();
        System.out.printf("Completed in %f seconds%n", ((double) (end - start)) / 1e9d);

        System.out.println("Execution complete");
        if (!runningJob.isSuccessful()) {
            //print the failure information, then exit with a bad error code so that we know something went wrong
            System.out.println(runningJob.getFailureInfo());
            return 1;
        } else {
            //Emit the number of records written to the path, then return 0 cause things are nice
            Path p = new Path(strings[1]);
            FileSystem fs = FileSystem.get(getConf());
            RemoteIterator<LocatedFileStatus> files = fs.listFiles(p, false);
            while (files.hasNext()) {
                LocatedFileStatus next = files.next(); //should just be one again, but you never know
                Path toRead = next.getPath();
                try (InputStream is = fs.open(toRead)) {
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                        String s;
                        while ((s = br.readLine()) != null) { //this should just be one line, but just in case
                            String[] split = s.split("\t");
                            String val = split[1];
                            System.out.printf("Total Lines: %s%n", val);
                        }
                    }
                }
            }
            return 0;
        }
    }

    public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, BooleanWritable, LongWritable> {
        private final LongWritable one = new LongWritable(1);
        private final BooleanWritable word = new BooleanWritable(true);

        public void map(LongWritable key, Text value, OutputCollector<BooleanWritable, LongWritable> output, Reporter reporter) throws IOException {
            output.collect(word, one);
        }
    }

    public static class Combine extends MapReduceBase implements Reducer<BooleanWritable, LongWritable, BooleanWritable, LongWritable> {
        private final LongWritable v = new LongWritable();

        public void reduce(BooleanWritable key, Iterator<LongWritable> values, OutputCollector<BooleanWritable, LongWritable> output, Reporter reporter) throws IOException {
            long count = 0l;
            while (values.hasNext()) {
                count++;
                values.next();
            }
            v.set(count);
            output.collect(key, v);
        }

    }

    public static class Reduce extends MapReduceBase implements Reducer<BooleanWritable, LongWritable, BooleanWritable, LongWritable> {
        private final LongWritable v = new LongWritable();

        public void reduce(BooleanWritable key, Iterator<LongWritable> values, OutputCollector<BooleanWritable, LongWritable> output, Reporter reporter) throws IOException {
            long sum = 0l;
            while (values.hasNext()) {
                sum += values.next().get();
            }
            v.set(sum);
            output.collect(key, v);
        }

    }
}
