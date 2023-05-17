package me.someoverflow.someutils.log;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author SomeOverflow
 */
public abstract class SomeLogFormatter {

    public static SimpleDateFormat DEFAULT_LOG_FORMAT = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    /**
     * Formats a string with the given data
     * (Example: "12-15-2021 15:16:20 - INFO || System : Example message")
     *
     * @param logger The name of the Logger who should write
     * @param logLevel The {@link me.someoverflow.someutils.log.SomeLogger.LogLevel LogLevel} of the message
     * @param message The message
     * @return The formatted message
     */
    public abstract String format(String logger, SomeLogger.LogLevel logLevel, String message);

    /**
     * Default formatter if none is given.
     * Example: "12-15-2021 15:16:20 - INFO || System : Example message"
     */
    public static class Default extends SomeLogFormatter {
        @Override
        public String format(String logger, SomeLogger.@NotNull LogLevel logLevel, String message) {
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(System.currentTimeMillis());

            return DEFAULT_LOG_FORMAT.format(cal.getTime())
                    + " - "
                    + logLevel.getName()
                    + " | "
                    + logger
                    + " : "
                    + message;
        }
    }
}