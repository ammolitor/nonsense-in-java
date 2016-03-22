package org.ammolitor.constants;

public class Constants {

    protected Constants() {
    }
        // SMALLINT
        public static final int SMALLINT_MIN = Short.MIN_VALUE;
        public static final int SMALLINT_MAX = Short.MAX_VALUE;

        // INTEGER
        public static final int INTEGER_MIN = Integer.MIN_VALUE;
        public static final int INTEGER_MAX = Integer.MAX_VALUE;

        // BIGINT
        public static final long BIGINT_MIN = Long.MIN_VALUE;
        public static final long BIGINT_MAX = Long.MAX_VALUE;

        // REAL
        public static final float REAL_MIN = -3.402E+38F;
        public static final float REAL_MAX = 3.402E+38F;
        public static final float REAL_SMALLEST_POS = 1.175E-37F;
        public static final float REAL_LARGEST_NEG = -1.175E-37F;

        // DOUBLE
        public static final double DOUBLE_MIN = -1.79769E+308;
        public static final double DOUBLE_MAX = 1.79769E+308;
        public static final double DOUBLE_SMALLEST_POS = 2.225E-307;
        public static final double DOUBLE_LARGEST_NEG = -2.225E-307;

    /*
    // FLOAT
    The default precision for FLOAT is 53, which is equivalent to DOUBLE PRECISION.
    A precision of 23 or less makes FLOAT equivalent to REAL.
    A precision of 24 or greater makes FLOAT equivalent to DOUBLE PRECISION.
     */
        public static final float FLOATREAL_MIN = REAL_MIN;
        public static final float FLOATREAL_MAX = REAL_MAX;
        public static final float FLOATREAL_SMALLEST_POS = REAL_SMALLEST_POS;
        public static final float FLOATREAL_LARGEST_NEG = REAL_LARGEST_NEG;

        public static final int FLOAT_MIN_PRECISION = 1;
        public static final int FLOAT_MAX_PRECISION = 256;

        public static final double FLOATDOUBLE_MIN = DOUBLE_MIN;
        public static final double FLOATDOUBLE_MAX = DOUBLE_MAX;
        public static final double FLOATDOUBLE_SMALLEST_POS = DOUBLE_SMALLEST_POS;
        public static final double FLOATDOUBLE_LARGEST_NEG = DOUBLE_LARGEST_NEG;

        // DECIMAL
        public static final int DECIMAL_MIN_PRECISION = 1;
        public static final int DECIMAL_MAX_PRECISION = 31;
        public static final int DECIMAL_MIN_SCALE = 1;
        public static final int DECIMAL_MAX_SCALE = 31;


    /*
    // NUMERIC
    synonym for DECIMAL
     */
        public static final int NUMERIC_MIN_PRECISION = DECIMAL_MIN_PRECISION;
        public static final int NUMERIC_MAX_PRECISION = DECIMAL_MAX_PRECISION;
        public static final int NUMERIC_MIN_SCALE = DECIMAL_MIN_SCALE;
        public static final int NUMERIC_MAX_SCALE = DECIMAL_MAX_SCALE;

        // DATE
        public static final int DATE_MIN_YEAR = 0;
        public static final int DATE_MAX_YEAR = 8099;
        public static final int DATE_MIN_MONTH = 0;
        public static final int DATE_MAX_MONTH = 11;
        public static final int DATE_MIN_DAY = 1;
        public static final int DATE_MAX_DAY = 31;

        // TIME
        public static final int TIME_MIN_HOURS = 0;
        public static final int TIME_MAX_HOURS = 23;
        public static final int TIME_MIN_MINUTES = 0;
        public static final int TIME_MAX_MINUTES = 59;
        public static final int TIME_MIN_SECONDS = 0;
        public static final int TIME_MAX_SECONDS = 59;

    /*
    // TIMESTAMP
    from ~21 Sep 1677 00:12:44 GMT to ~11 Apr 2262 23:47:16 GMT
    */
        private static final int MICROS_TO_SECOND = 1000000;
        public static final long TIMESTAMP_MIN = Long.MIN_VALUE / MICROS_TO_SECOND + 1;
        public static final long TIMESTAMP_MAX = Long.MAX_VALUE / MICROS_TO_SECOND - 1;
        public static final long TIMESTAMP_COMMON_MIN = 0; // UNIX zero
        public static final long TIMESTAMP_COMMON_MAX = 2147483646; // UNIX 2038-01-19 03:14:06
        // CHAR
        public static final int CHAR_MIN_LEN = 1;
        public static final int CHAR_MAX_LEN = 254;

        // VARCHAR
        public static final int VARCHAR_MIN_LEN = 1;
        public static final int VARCHAR_MAX_LEN = 32672;

        // LONGVARCHAR
        public static final int LONGVARCHAR_MIN_LEN = 0;
        public static final int LONGVARCHAR_MAX_LEN = 32700;

        // BINARY
        // CHAR FOR BIT DATA
        public static final int CHAR_FOR_BIT_DATA_MIN_LEN = 1;
        public static final int CHAR_FOR_BIT_DATA_MAX_LEN = 254;

        // VARBINARY
        // VARCHAR FOR BIT DATA
        public static final int VARCHAR_FOR_BIT_DATA_MIN_LEN = 1;
        public static final int VARCHAR_FOR_BIT_DATA_MAX_LEN = 32672;

        // LONGVARBINARY
        // LONG_VARCHAR FOR BIT DATA
        public static final int LONG_VARCHAR_FOR_BIT_DATA_MIN_LEN = 1;
        public static final int LONG_VARCHAR_FOR_BIT_DATA_MAX_LEN = 32700;

        // BOOLEAN
        // public static final int BOOLEAN_MIN = 0;
        // public static final int BOOLEAN_MAX = 1;

        // BLOB
        public static final int BLOB_MIN_LEN = 1;
        public static final int BLOB_MAX_LEN = 2147483647;
        public static final int BLOB_MAX_LEN_K = 2097152; // not sure about this as the max is (2^31)-1 this is 2^31/1024
        public static final int BLOB_MAX_LEN_M = 2048;    // not sure about this as the max is (2^31)-1 this is 2^31/1024/1024
        public static final int BLOB_MAX_LEN_G = 2;       // not sure about this as the max is (2^31)-1 this is 2^31/1024/1024/1024

        // CLOB
        public static final int CLOB_MIN_LEN = 1;
        public static final int CLOB_MAX_LEN = 2147483647;
        public static final int CLOB_MAX_LEN_K = 2097152; // not sure about this as the max is (2^31)-1 this is 2^31/1024
        public static final int CLOB_MAX_LEN_M = 2048;    // not sure about this as the max is (2^31)-1 this is 2^31/1024/1024
        public static final int CLOB_MAX_LEN_G = 2;       // not sure about this as the max is (2^31)-1 this is 2^31/1024/1024/1024

    }
