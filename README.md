# SomeUtils

Just a simple & stupid Library for Java

I really dont know why you should use this,
but I am going to use this for my Projects ;)

## Installation

Gradle
```gradle
maven {
    url "https://repo.someworkflow.de/releases"
}

implementation "me.someoverflow:someutils:1.0.6"
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
  <version>1.0.6</version>
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
        defaults.addDes("Description", SomeFile.DESCRIPTION);
        defaults.add("testString", "test");
        defaults.add("testInt", "1");
        defaults.add("testBool", "false");
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
        file.read(); // => List<String>
        file.write(value1, value2 /*, ... */);
        file.override(value1, value2 /*, ... */);
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
        config.getString(path); // => String
        config.getInt(path); // => Integer
        config.getBoolean(path); // => Boolean
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
        logger.setConsoleFormatter(new SomeLogFormatter() {
            @Override
            public String format(String logger, SomeLogger.LogLevel logLevel, String message) {
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
        SomeLogger logger = new SomeLogger("name", true, "path/", "name.custom");
    }
}
```

Change the custom Formatter in the File
```java
class Example {
    public static void main(String[] args) {
        logger.setFileFormatter(new SomeLogFormatter() {
            @Override
            public String format(String logger, SomeLogger.LogLevel logLevel, String message) {
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
        logger.setToConsole(true / false);
    }
}
```

Disable/Enable Debugging
```java
class Example {
    public static void main(String[] args) {
        logger.setDebugging(true / false);
    }
}
```

Log a Message
```java
class Example {
    public static void main(String[] args) {
        logger.info(message);
        logger.error(message);
        logger.warn(message);
        logger.debug(message);
    }
}
```

Log a Message with another Formatter
```java
class Example {
    public static void main(String[] args) {
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

