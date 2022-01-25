# SomeUtils

Just a simple & stupid Library for Java

I really don't know why you should use this,
but I am going to use this for my Projects ;)

## Installation

Gradle
```gradle
maven {
    url "https://repo.someworkflow.de/releases"
}

implementation "me.someoverflow:someutils:1.1.1"
```

Maven
```gradle
<repository>
  <id>somerepo-releases</id>
  <name>Some Repository</name>
  <url>https://repo.someworkflow.de/releases</url>
</repository>

<dependency>
  <groupId>me.someoverflow</groupId>
  <artifactId>someutils</artifactId>
  <version>1.1.1</version>
</dependency>
```
    
## File Usage/Examples

Create/Get a File
```java
class Example {
    public static void main(String[] args) {
        // Path should end with / ,and the file extension should not matter
        SomeFile file = new SomeFile("path/", "file.name");
    }
}
```

Set Defaults for a File
```java
class Example {
    public static void main(String[] args) {
        SomeDefaults defaults = new SomeDefaults<>();
        // Add a Description
        defaults.addDes("Description");
        // Add a String
        defaults.add("testString", "test");
        // Add a Integer
        defaults.add("testInt", "1");
        // Add a Boolean
        defaults.add("testBool", "false");
        // Add a List
        defaults.addList("ListExample",
                new ArrayList<>(Arrays.asList("test1", "test2", "test3", "bread", "tree"))
        );
        // Set the defaults finally
        file.setDefaults(defaults);
    }
}
```

Save the Defaults to the File
```java
class Example {
    public static void main(String[] args) {
        file.saveDefaults();
    }
}
```

Read / Write to the File
```java
class Example {
    public static void main(String[] args) {
        // Read the lines from the file
        file.read(); // => List<String>
        // Write new lines to a file
        file.write(value1, value2 /*, ... */);
        // Override a file complete
        file.override(value1, value2 /*, ... */);
        // Change a specific string in the file
        file.change(oldLine, newLine);
        // Change a specific line in the file
        file.changeLine(line, newLine);
    }
}
```

Rename and Move a File
```java
class Example {
    public static void main(String[] args) {
        // Rename the File
        file.rename("new.name");
        // Move the File
        file.move("new/path/");
    }
}
```

Get a SomeConfig
```java
class Example {
    public static void main(String[] args) {
        SomeConfig config = new SomeConfig(file);
    }
}
```

Get values from a config
```java
class Example {
    public static void main(String[] args) {
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

[Full Example Class](https://github.com/SomeOverflow/SomeUtils/blob/master/src/test/java/FileExample.java)
## Logger Usage/Examples

Default Logger
```java
class Example {
    public static void main(String[] args) {
        SomeLogger logger = new SomeLogger("name");
    }
}
```

Logger with custom Formatter
```java
class Example {
    public static void main(String[] args) {
        SomeLogger logger = new SomeLogger("name", new SomeLogFormatter() {
            @Override
            public String format(String logger, SomeLogger.LogLevel logLevel, String message) {
                // Returns the message which will be written everywhere
                return message;
            }
        });
    }
}
```

Change the custom Formatter in the Console
```java
class Example {
    public static void main(String[] args) {
        // Change the Formatter
        logger.setConsoleFormatter(new SomeLogFormatter() {
            @Override
            public String format(String logger, SomeLogger.LogLevel logLevel, String message) {
                // Returns the message which will be written in the console
                return message;
            }
        });
    }
}
```

Logger with File
```java
class Example {
    public static void main(String[] args) {
        // Create the Logger
        SomeLogger logger = new SomeLogger("name", true, "path/", "name.custom");
    }
}
```

Change the custom Formatter in the File
```java
class Example {
    public static void main(String[] args) {
        // Set the formatter for the file
        logger.setFileFormatter(new SomeLogFormatter() {
            @Override
            public String format(String logger, SomeLogger.LogLevel logLevel, String message) {
                // Returns the message which will be written in the file
                return message;
            }
        });
    }
}
```

Disable/Enable Console Logging
```java
class Example {
    public static void main(String[] args) {
        // Change if the logger should write to the Console
        logger.setToConsole(true / false);
    }
}
```

Disable/Enable Debugging
```java
class Example {
    public static void main(String[] args) {
        // Change if logger.debug() will be logged
        logger.setDebugging(true / false);
    }
}
```

Log a Message
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
    }
}
```

Log a Message with another Formatter
```java
class Example {
    public static void main(String[] args) {
        // Log a message with custom formatter
        logger.logWF(SomeLogger.LogLevel.INFO, "Test", new SomeLogFormatter() {
            @Override
            public String format(String logger, SomeLogger.LogLevel logLevel, String message) {
                return message;
            }
        });
    }
}
```

[Full Example Class](https://github.com/SomeOverflow/SomeUtils/blob/master/src/test/java/LogExample.java)

## Support

More Info: https://www.someworkflow.de/contact

## Badges

[![Version](https://repo.someworkflow.de/api/badge/latest/releases/me/someoverflow/someutils?color=40c14a&name=SomeUtils&prefix=v)](https://repo.someworkflow.de/#/snapshots/me/someoverflow/someutils)

[![MIT License](https://img.shields.io/apm/l/atomic-design-ui.svg?)](https://github.com/SomeOverflow/SomeUtils/blob/master/LICENSE)
## License

[MIT](https://choosealicense.com/licenses/mit/)

