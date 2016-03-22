package org.ammolitor;

import org.ammolitor.constants.Constants;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DataMaker {

    public static void main(String args[]) throws Exception {
        Timestamp ts;
        List<String> rows = new ArrayList<String>();
        for (long i = Constants.TIMESTAMP_MIN; i <= Constants.TIMESTAMP_MAX; i+=1000) {
            ts = new Timestamp(i);
            rows.add(i + "," + ts.toString() + "\n");
            if (rows.size() == 1000000) {
                writeBuffered(rows, 1048576);
                System.out.print(i + " ");
                rows.clear();
            }
        }
        writeBuffered(rows, 1048576);
    }

    private static void writeBuffered(List<String> records, int bufSize) throws IOException {
        File file = new File("data.csv");
        FileWriter writer = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer, bufSize);

        System.out.println("Writing data");
        write(records, bufferedWriter);
    }

    private static void write(List<String> records, Writer writer) throws IOException {
        for (String record : records) {
            writer.write(record);
        }
        writer.flush();
        writer.close();
    }
}
