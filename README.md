# SomeUtils

### Just a simple library for Java

Versions 1-7 have been removed due to a fatal bug

It's _not_ recommended to use this lib! <br />
It is still _bad_ and therefore _expandable_. <br />
If you still use the lib, please write if there are problems under the issues tab!

## Installation
Gradle
```gradle
maven {
    url "https://repo.someworkflow.de/releases"
}

implementation "me.someoverflow:someutils:1.1.9"
```

Maven
```gradle
<repository>
  <id>some-repo-releases</id>
  <name>somerepo</name>
  <url>https://repo.someworkflow.de/releases</url>
</repository>

<dependency>
  <groupId>me.someoverflow</groupId>
  <artifactId>someutils</artifactId>
  <version>1.1.9</version>
</dependency>
```

## File Usage/Examples

[Full Example Class](https://github.com/SomeOverflow/SomeUtils/blob/master/src/test/java/me/someoverflow/someutils/FileExample.java)

Create / Get a file
```java
class Example {
    public static void main(String[] args) {
        // Path should end with a slash, and the file extension should not matter
        // Leave the path empty for the execution path
        SomeFile file = new SomeFile("path/", "file.name"); // => SomeFile
    }
}
```

Rename, Move and Delete a file
```java
class Example {
    public static void main(String[] args) {
        // Rename the File (deletes the file if it already existed)
        file.rename("new.name"); // => HashMap<String, Object> with the self-explaining keys deleteOldFile deleteNewFile
        
        // Move the File ! todo: check if the file exists
        file.move("new/path/"); // => HashMap<String, Object> with the self-explaining keys createdDirectory deletedOldFile
        
        // Delete the file
        file.delete(); // => boolean if deleted
    }
}
```

Read / Write to a file
```java
class Example {
    public static void main(String[] args) {
        // Read the lines from the file into a list
        file.read(); // => List<String>
        
        // Write new lines to a file
        file.write(value1, value2 /* ... */);
        // Override a file complete
        file.override(value1, value2 /* ... */);
        // Change a specific string in the file
        file.change(oldLine, newLine);
        // Change a specific line in the file
        file.changeLine(line, newLine);
    }
}
```

Set and Save the defaults to a file
```java
class Example {
    public static void main(String[] args) {
        SomeDefaults defaults = new SomeDefaults<>();
        
        // Add a Description (hashtag will be auto-added)
        defaults.addDescription("Description");
        // Add plain line without a hashtag
        defaults.addPlain("# Custom Line");
        
        // Add a String
        defaults.add("testString", "test");
        // Add a Integer
        defaults.add("testInt", 1);
        // Add a Boolean
        defaults.add("testBool", false);
        // Add a List
        defaults.addList("ListExample",
                new ArrayList<>(Arrays.asList("test1", "test2", "test3", true, 123))
        );
        
        // Set the defaults
        file.setDefaults(defaults);
        // Creates the file with the defaults if it doesn't exist
        file.saveDefaults();
    }
}
```

Get a SomeConfig and their values
```java
class Example {
    public static void main(String[] args) {
        SomeConfig config = new SomeConfig(file);

        // Get a String
        config.getString(path); // => String
        // Get a Integer
        config.getInt(path); // => Integer
        // Get a Boolean
        config.getBoolean(path); // => Boolean
        // Get a List
        config.getList(path); // => List<String>
    }
}
```

## Logger Usage/Examples

[Full Example Class](https://github.com/SomeOverflow/SomeUtils/blob/master/src/test/java/me/someoverflow/someutils/LogExample.java)

Get a logger with:
 - Name
 - Name & Formatter
 - Name & File to log to
```java
class Example {
    public static void main(String[] args) {
        // With name
        SomeLogger logger = new SomeLogger("name");

        // With name and custom Formatter
        SomeLogger logger = new SomeLogger("name", new SomeLogFormatter() {
            @Override
            public String format(String logger, SomeLogger.LogLevel logLevel, String message) {
                // Returns the message which will be written everywhere
                return message;
            }
        });
        
        // With a name and a file to log to
        SomeLogger logger = new SomeLogger("name", true, "path/", "name.custom");
    }
}
```

Log a Message and change color for a LogLevel
```java
class Example {
    public static void main(String[] args) {
        // Log a info message
        logger.info(message);
        // Log a error message
        logger.error(message);
        // Log a warning message
        logger.warn(message);
        // Log a debug message
        logger.debug(message);
        
        // Change color/prefix of a LogLevel
        logger.changeColor(LogLevel.INFO, colorOrPrefix);
        
        // Log a message with custom formatter
        logger.logWF(SomeLogger.LogLevel.INFO, message, new SomeLogFormatter() {
            @Override
            public String format(String logger, SomeLogger.LogLevel logLevel, String message) {
                return message;
            }
        });
    }
}
```

Change the formatter:
 - In Console
 - In File
 - Both
```java
class Example {
    public static void main(String[] args) {
        // Set the formatter for the console
        logger.setConsoleFormatter(new SomeLogFormatter() {
            @Override
            public String format(String logger, SomeLogger.LogLevel logLevel, String message) {
                // Returns the message which will be written in the console
                return message;
            }
        });

        // Set the formatter for the file
        logger.setFileFormatter(new SomeLogFormatter() {
            @Override
            public String format(String logger, SomeLogger.LogLevel logLevel, String message) {
                // Returns the message which will be written in the file
                return message;
            }
        });


        // Set both formatters
        logger.setBothFormatter(new SomeLogFormatter() {
            @Override
            public String format(String logger, SomeLogger.LogLevel logLevel, String message) {
                // Returns the message which will be written in the file
                return message;
            }
        });
    }
}
```

Toggle:
 - Console Logging
 - Debugging
```java
class Example {
    public static void main(String[] args) {
        // Change if the logger should write to the Console
        logger.setToConsole(true / false);
        
        // Change if logger.debug() will be logged
        logger.setDebugging(true / false);
    }
}
```

## Support

Feel free to contribute and create issues!

## Badges

[![Version](https://repo.someworkflow.de/api/badge/latest/releases/me/someoverflow/someutils?color=40c14a&name=SomeUtils&prefix=v)](https://repo.someworkflow.de/#/releases/me/someoverflow/someutils)

## License
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://raw.githubusercontent.com/SomeOverflow/SomeUtils/master/LICENSE)