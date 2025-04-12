package controller;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogHandler {
    private static final Logger LOGGER = Logger.getLogger(LogHandler.class.getName());
    private static final String LOG_FILE = "application.log";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss, dd/MM/yyyy");
    private static boolean isFirstWrite = true;

    public static void logInfo(String message) {
        String logMessage = String.format("[%s] INFO: %s%n", DATE_FORMAT.format(new Date()), message);
        LOGGER.info(message);
        writeToFile(logMessage);
    }

    public static void logError(String message) {
        String logMessage = String.format("[%s] ERROR: %s%n", DATE_FORMAT.format(new Date()), message);
        LOGGER.severe(message);
        writeToFile(logMessage);
    }

    public static void logError(String message, Throwable throwable) {
        String logMessage = String.format("[%s] ERROR: %s - %s%n", 
            DATE_FORMAT.format(new Date()), message, throwable.getMessage());
        LOGGER.log(Level.SEVERE, message, throwable);
        writeToFile(logMessage);
    }

    private static void writeToFile(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, !isFirstWrite)) {
            if (isFirstWrite) {
                isFirstWrite = false;
            }
            writer.write(message);
        } catch (IOException e) {
            LOGGER.severe("Failed to write log to file: " + e.getMessage());
        }
    }
}