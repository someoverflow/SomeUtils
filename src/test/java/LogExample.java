import me.someoverflow.someutils.log.ConsoleColors;
import me.someoverflow.someutils.log.SomeLogFormatter;
import me.someoverflow.someutils.log.SomeLogger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author SomeOverflow
 */
public class LogExample {

    public static void main(String[] args) {
        // Create the Logger
        SomeLogger logger = new SomeLogger("LogExample", true, "example/", "config.example");
        // Change the ConsoleFormatter
        logger.setConsoleFormatter(new SomeLogFormatter() {
            @Override
            public String format(String logger, SomeLogger.LogLevel logLevel, String message) {
                SimpleDateFormat logTime = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
                Calendar cal = new GregorianCalendar();
                cal.setTimeInMillis(System.currentTimeMillis());

                return logTime.format(cal.getTime())
                        + " - "
                        + logLevel
                        + " >> "
                        + logger
                        + " > "
                        + message;
            }
        });

        // Change the FileFormatter
        logger.setFileFormatter(new SomeLogFormatter() {
            @Override
            public String format(String logger, SomeLogger.LogLevel logLevel, String message) {
                return " | " + logger + " > " + message;
            }
        });

        // Change the info logging to black bold
        logger.changeColor(SomeLogger.LogLevel.INFO, ConsoleColors.BLACK_BOLD.color);

        // Log with custom formatter
        logger.logWithFormatter(SomeLogger.LogLevel.WARNING, "Warn Test", new SomeLogFormatter.Default());

        logger.info("Info Test");

        // Enable debugging
        logger.setDebugging(true);
        logger.debug("Debug Test");

        logger.error("Error Test");

        logger.log(SomeLogger.LogLevel.WARNING, "Another Warn Test");
    }

}