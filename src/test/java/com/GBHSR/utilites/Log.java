package com.GBHSR.utilites;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log 
{
//    public static Logger logger = LogManager.getLogger(Log.class);
//    static StringBuilder logBuffer;
    
    public static Logger logger = LogManager.getLogger(Log.class);
    static StringBuilder logBuffer = new StringBuilder();
    
    
    public static void addLog(String message) {

        try {
            String logMessage = LocalTime.now() + " - " + message;
            logBuffer.append(logMessage).append("\n");
            File logFolder = new File(System.getProperty("user.dir") + "/logs");
            if (!logFolder.exists()) {
                boolean created = logFolder.mkdirs();
                System.out.println("Folder Created: " + created);
            }

            String fileName = System.getProperty("user.dir")
                    + "/Logs/TestLog_"+ java.time.LocalDate.now()+ ".txt";
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(logMessage + System.lineSeparator());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getLogs() {
        return logBuffer.toString();
    }

    public static void clearLogs() {
        logBuffer.setLength(0);
    }
}
	
