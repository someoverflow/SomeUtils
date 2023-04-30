package me.someoverflow.someutils;

import me.someoverflow.someutils.log.ConsoleColors;
import me.someoverflow.someutils.log.SomeLogFormatter;
import me.someoverflow.someutils.log.SomeLogger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogTest {

    SomeLogger logger;
    final String loggerName = "testLogger";
    SomeLogFormatter formatter = new SomeLogFormatter.Default();

    @BeforeEach
    void setupLogger() {
        logger = new SomeLogger(loggerName, true, "", "test.log");
        logger.setDebugging(true);
        logger.setBothFormatter(formatter);
    }

    @Nested
    class ConsoleTest {

        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

        private final PrintStream originalOut = System.out;
        private final PrintStream originalErr = System.err;

        @BeforeEach
        public void setUpStreams() {
            System.setOut(new PrintStream(outContent));
            System.setErr(new PrintStream(errContent));
        }

        @AfterEach
        public void restoreStreams() {
            System.setOut(originalOut);
            System.setErr(originalErr);
        }

        @Test
        @DisplayName("Test LogLevel outputs")
        void testOut() {
            StringBuilder terminal = new StringBuilder();
            String message = "";
            String format = "";

            // Debug
            message = "Test Debug";

            logger.log(SomeLogger.LogLevel.DEBUG, message);
            logger.debug(message);

            format = formatter.format(loggerName, SomeLogger.LogLevel.DEBUG, message);
            terminal.append(format).append(ConsoleColors.RESET).append("\r\n");
            format = formatter.format(loggerName, SomeLogger.LogLevel.DEBUG, SomeLogger.debugColor + message);
            terminal.append(format).append(ConsoleColors.RESET).append("\r\n");

            assertEquals(
                    terminal.toString(),
                    outContent.toString(),
                    "Check if the thing that got logged is in the out stream"
            );

            // Info
            message = "Info Debug";

            logger.log(SomeLogger.LogLevel.INFO, message);
            logger.info(message);

            format = formatter.format(loggerName, SomeLogger.LogLevel.INFO, message);
            terminal.append(format).append(ConsoleColors.RESET).append("\r\n");
            format = formatter.format(loggerName, SomeLogger.LogLevel.INFO, SomeLogger.infoColor + message);
            terminal.append(format).append(ConsoleColors.RESET).append("\r\n");

            assertEquals(
                    terminal.toString(),
                    outContent.toString(),
                    "Check if the thing that got logged is in the out stream"
            );

            // Warning
            message = "Warning Debug";

            logger.log(SomeLogger.LogLevel.WARNING, message);
            logger.warn(message);

            format = formatter.format(loggerName, SomeLogger.LogLevel.WARNING, message);
            terminal.append(format).append(ConsoleColors.RESET).append("\r\n");
            format = formatter.format(loggerName, SomeLogger.LogLevel.WARNING, SomeLogger.warnColor + message);
            terminal.append(format).append(ConsoleColors.RESET).append("\r\n");

            assertEquals(
                    terminal.toString(),
                    outContent.toString(),
                    "Check if the thing that got logged is in the out stream"
            );

            // Error
            message = "Error Debug";

            logger.log(SomeLogger.LogLevel.ERROR, message);
            logger.error(message);

            terminal = new StringBuilder();

            format = formatter.format(loggerName, SomeLogger.LogLevel.ERROR, message);
            terminal.append(format).append(ConsoleColors.RESET).append("\r\n");
            format = formatter.format(loggerName, SomeLogger.LogLevel.ERROR, SomeLogger.errorColor + message);
            terminal.append(format).append(ConsoleColors.RESET).append("\r\n");

            assertEquals(
                    terminal.toString(),
                    errContent.toString(),
                    "Check if the thing that got logged is in the out stream"
            );
        }
    }

    // TODO: 30.04.2023 Log-File Tests
    // TODO: 30.04.2023 Formatter Tests
}
