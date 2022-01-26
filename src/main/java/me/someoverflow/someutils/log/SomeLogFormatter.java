package me.someoverflow.someutils.log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author SomeOverflow
 */
public abstract class SomeLogFormatter {

    /**
     * Formats a string with the given data
     * (Returns something like "12-15-2021 15:16:20 - INFO || System : Test message")
     *
     * @param logger The name of the Logger who should write
     * @param logLevel The {@link me.someoverflow.someutils.log.SomeLogger.LogLevel LogLevel} of the Message
     * @param message The message
     * @return The formatted message
     */
    public abstract String format(String logger, SomeLogger.LogLevel logLevel, String message);

    public static class Default extends SomeLogFormatter {
        @Override
        public String format(String logger, SomeLogger.LogLevel logLevel, String message) {
            SimpleDateFormat logTime = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(System.currentTimeMillis());

            return logTime.format(cal.getTime())
                    + " - "
                    + logLevel
                    + " || "
                    + logger
                    + " : "
                    + message;
        }
    }
}
