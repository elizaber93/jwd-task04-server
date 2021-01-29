package by.epam.training.javaWEB.task04.server.exception;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    private static Logger logger;

    public Log() {

    }

    public static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("ServerLog");
        }
        FileHandler logFile;
        try {
            logFile = new FileHandler("LogFile.log");
            logger.addHandler(logFile);
            SimpleFormatter formatter = new SimpleFormatter();
            logFile.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        return logger;
    }
}
