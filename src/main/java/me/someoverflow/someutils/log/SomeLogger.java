package me.someoverflow.someutils.log;

import me.someoverflow.someutils.file.SomeFile;

import java.io.IOException;

/**
 * @author SomeOverflow
 */
public class SomeLogger {

    private final String name;

    private final SomeFile fileManager;
    private final boolean toFile;
    private boolean toConsole;

    private boolean debugging;

    private SomeLogFormatter defaultFormatter;

    public SomeLogger(String name) {
        this.name             = name;
        this.defaultFormatter = new SomeLogFormatter.Default();

        this.toConsole        = true;

        this.toFile           = false;
        this.fileManager      = null;
    }
    public SomeLogger(String name, SomeLogFormatter formatter) {
        this.name             = name;
        this.defaultFormatter = formatter;

        this.toConsole        = true;

        this.toFile           = false;
        this.fileManager      = null;
    }
    public SomeLogger(String name, boolean toConsole, String filePath, String fileName) {
        this.name             = name;
        this.defaultFormatter = new SomeLogFormatter.Default();

        this.toConsole        = toConsole;

        this.toFile           = true;
        this.fileManager      = new SomeFile(filePath, fileName);
    }

    /**
     * Set whether the logger should log {@link LogLevel Debug LogLevel's}
     *
     * @param debugging should he debug
     */
    public void setDebugging(boolean debugging) {
        this.debugging = debugging;
    }
    public boolean isDebugging() {
        return debugging;
    }

    /**
     * Set whether the logger should log to the console
     *
     * @param toConsole should he write to the console
     */
    public void setToConsole(boolean toConsole) {
        this.toConsole = toConsole;
    }

    /**
     * Set the format in which everything will be output
     *
     * @param defaultFormatter Format
     */
    public void setFormatter(SomeLogFormatter defaultFormatter) {
        this.defaultFormatter = defaultFormatter;
    }
    // TODO: 31.12.2021 set Formatter for Console & File different

    /**
     * Log a message with the given {@link LogLevel}
     *
     * @param logLevel {@link LogLevel}
     * @param message The message to be output
     */
    public void log(LogLevel logLevel, String message) {
        message = message + ConsoleColors.RESET;
        if (toConsole && !debugging) System.out.println(defaultFormatter.format(name, logLevel, message));
        if (toFile) {
            try {
                assert fileManager != null;
                fileManager.write(defaultFormatter.format(name, logLevel, message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Log a message with the given {@link SomeLogFormatter Formatter}
     *
     * @param logLevel {@link LogLevel The LogLevel of the Log}
     * @param message The message to be logged
     * @param formatter {@link SomeLogFormatter The Formatter}
     */
    public void logWF(LogLevel logLevel, String message, SomeLogFormatter formatter) {
        message = message + ConsoleColors.RESET;
        if (toConsole && !debugging) System.out.println(formatter.format(name, logLevel, message));
        if (toFile) {
            try {
                assert fileManager != null;
                fileManager.write(defaultFormatter.format(name, logLevel, message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Doing this:
     * {@link SomeLogger#log(LogLevel, String)}
     * with 'LogLevel.ERROR'
     * @param message The message that is to be output
     */
    public void error(String message) {
        log(LogLevel.ERROR, ConsoleColors.RED + message);
    }
    /**
     * Doing this:
     * {@link SomeLogger#log(LogLevel, String)}
     * with 'LogLevel.WARNING'
     * @param message The message that is to be output
     */
    public void warn(String message) {
        log(LogLevel.WARNING, ConsoleColors.RED_BRIGHT + message);
    }
    /**
     * Doing this:
     * {@link SomeLogger#log(LogLevel, String)}
     * with 'LogLevel.INFO'
     * @param message The message that is to be output
     */
    public void info(String message) {
        log(LogLevel.INFO, ConsoleColors.CYAN + message);
    }
    /**
     * Doing this:
     * {@link SomeLogger#log(LogLevel, String)}
     * with 'LogLevel.DEBUG'
     * ( Logged only when {@link SomeLogger#isDebugging()} is on )
     * @param message The message that is to be output
     */
    public void debug(String message) {
        log(LogLevel.DEBUG, ConsoleColors.GREEN + message);
    }

    /**
     * Every LogLevel with the "name"
     */
    public enum LogLevel {
        ERROR("EROR"), WARNING("WARN"), INFO("INFO"), DEBUG("DBUG");

        private final String name;
        LogLevel(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }
}
