package org.example.util;


import org.apache.log4j.Logger;

public class LogLog4j {

// Initialize Log4j log s

    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());//

    public static int rowCount = 0;
    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite

    public static void startTestCase(String sTestCaseName) {
        Log.info("");
        Log.info("");
        Log.info(" *******************************************************************");
        Log.info(rowCount+".                   Test Case: " + sTestCaseName);
        Log.info(" *******************************************************************");
        rowCount++;

    }
    public static void parameter (String parameterName, String parameterValue){
        Log.info(" ==> Parameter " + parameterName + " = '" + parameterValue + "'");
    }

    //This is to print log for the ending of the test case
    public static void startMethod(String methodName) {

        Log.info(rowCount+". ########## Method " + methodName + " STARTED ##########");
         rowCount++;
    }

    public static void endMethod(String methodName) {

        Log.info(rowCount+". ########## Method " + methodName + "  ENDED ##########");
    //    Log.info("");
         rowCount++;
    }

    public static void endTestCase2() {
        Log.info("");
        Log.info(" *******************************************************************");
        Log.info(rowCount+".                  -----Test case was finished------                ");
        Log.info(" *******************************************************************");
        Log.info("");
        Log.info("");
        Log.info("");
        rowCount = 0;
    }



    public static void info(String message) {

        Log.info(rowCount+". =====>>  " + message + "  ==>");
        rowCount++;

    }


    public static void verify(String message) {

        Log.info("Verify: " + message);

    }

    public static void warn(String message) {

        Log.warn(message);

    }

    public static void error(String message) {

        Log.error(message);

    }

    public static void fatal(String message) {

        Log.fatal(message);

    }

    public static void debug(String message) {

        Log.debug(message);

    }

}
