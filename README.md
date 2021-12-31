# SomeUtils

Just a simple & stupid Library for Java

I really dont know why you should use this,
but I am going to use this for my Projects ;)

## Installation

Gradle
```gradle
maven {
    url "https://repo.someworkflow.de/snapshots"
}

implementation "me.someoverflow:someutils:1.0.3"
```

Maven
```gradle
<repository>
  <id>somerepo-snapshots</id>
  <name>Some Repository</name>
  <url>https://repo.someworkflow.de/snapshots</url>
</repository>

<dependency>
  <groupId>me.someoverflow</groupId>
  <artifactId>someutils</artifactId>
  <version>1.0.3</version>
</dependency>
```
    
## File Usage/Examples

Create/Get a File
```java
# Path should end with / ,and the file extension should not matter
SomeFile file = new SomeFile("path/", "file.name");
```

Set Defaults for a File
```java
SomeDefaults defaults = new SomeDefaults<>();
defaults.addDes("Description", SomeFile.DESCRIPTION);
defaults.add("testString", "test");
defaults.add("testInt", "1");
defaults.add("testBool", "false");
file.setDefaults(defaults);
```

Save the Defaults to the File
```java
file.saveDefaults();
```

Read / Write to the File
```java
file.read(); -> List<String>
file.write(value1, value2, ...);
file.override(value1, value2, ...);
```

Get a SomeConfig
```java
SomeConfig config = new SomeConfig(file);
```

Get values from a config
```java
config.getString(path); -> String
config.getInt(path); -> Integer
config.getBoolean(path); -> Boolean
```

[Full Example Class](https://github.com/SomeOverflow/SomeUtils/blob/master/src/test/java/FileExample.java)
## Logger Usage/Examples

Default Logger
```java
SomeLogger logger = new SomeLogger("name");
```

Logger with custom Formatter
```java
SomeLogger logger = new SomeLogger("name", new SomeLogFormatter() {
    @Override
        public String format(String logger, SomeLogger.LogLevel logLevel, String message) {
            return message;
    }
});
```

Logger with File
```java
SomeLogger logger = new SomeLogger("name", true, "path/", "name.custom");
```

Disable/Enable Console Logging
```java
logger.setToConsole(true / false);
```

Disable/Enable Debugging
```java
logger.setDebugging(true / false);
```

Log a Message
```java
logger.info(message);
logger.error(message);
logger.warn(message);
logger.debug(message);
```

Log a Message with another Formatter
```java
logger.logWF(SomeLogger.LogLevel.INFO, "Test", new SomeLogFormatter() {
    @Override
    public String format(String logger, SomeLogger.LogLevel logLevel, String message) {
        return message;
    }
});
```

[Full Example Class](https://github.com/SomeOverflow/SomeUtils/blob/master/src/test/java/LogExample.java)



## Support

More Info: https://www.someworkflow.de/contact


## Badges

[![Version](https://repo.someworkflow.de/api/badge/latest/snapshots/me/someoverflow/someutils?color=40c14a&name=SomeUtils&prefix=v)](https://repo.someworkflow.de/#/snapshots/me/someoverflow/someutils)

[![MIT License](https://img.shields.io/apm/l/atomic-design-ui.svg?)](https://github.com/SomeOverflow/SomeUtils/blob/master/LICENSE)
## License

[MIT](https://choosealicense.com/licenses/mit/)

