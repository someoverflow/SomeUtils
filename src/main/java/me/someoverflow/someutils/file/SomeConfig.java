package me.someoverflow.someutils.file;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SomeOverflow
 */
public class SomeConfig {

    private final SomeFile file;
    public SomeConfig(SomeFile file) {
        this.file = file;
    }

    // TODO: 31.12.2021 Other types & Lists

    /**
     * Get a specific String from the ConfigFile but only when it is like this in the file:
     * path: 'output'
     *
     * @param path The path in the config
     * @return The value
     */
    public String getString(String path) {
        String result = null;
        for (String s : file.read()) {
            if (s.startsWith(path + ": ")) {
                CharSequence res = stringBetween(s.replaceFirst(path + ": ", ""), "'", "'");
                if (res != null)
                    result = res.toString();
                break;
            }
        }

        return result;
    }
    /**
     * The same as {@link SomeConfig#getString(String)}
     * but when getString is null it will return the given def value
     *
     * @param path The path in the config
     * @param def The default value if its null
     * @return The value
     */
    public String getString(String path, String def) {
        String result = getString(path);
        if (result == null)
            return def;
        else
            return result;
    }

    /**
     * Get a specific Integer from the ConfigFile but only when it is like this in the file:
     * path: 'output'
     *
     * @param path The path in the config
     * @return The value
     */
    public Integer getInt(String path) {
        String result = getString(path);
        if (result == null) return null;
        return Integer.parseInt(result);
    }
    /**
     * The same as {@link SomeConfig#getInt(String)}
     * but when getInt is null it will return the given def value
     *
     * @param path The path in the config
     * @param def The default value if its null
     * @return The value
     */
    public int getInt(String path, int def) {
        Integer result = getInt(path);
        if (result == null)
            return def;
        else
            return result;
    }

    /**
     * Get a specific Double from the ConfigFile but only when it is like this in the file:
     * path: 'output'
     *
     * @param path The path in the config
     * @return The value
     */
    public Double getDouble(String path) {
        String result = getString(path);
        if (result == null) return null;
        return Double.parseDouble(result);
    }
    /**
     * The same as {@link SomeConfig#getDouble(String)}
     * but when getDouble is null it will return the given def value
     *
     * @param path The path in the config
     * @param def The default value if its null
     * @return The value
     */
    public Double getDouble(String path, double def) {
        Double result = getDouble(path);
        if (result == null)
            return def;
        else
            return result;
    }

    /**
     * Get a specific Boolean from the ConfigFile but only when it is like this in the file:
     * path: 'output'
     *
     * @param path The path in the config
     * @return The value
     */
    public Boolean getBoolean(String path) {
        return Boolean.parseBoolean(getString(path));
    }
    /**
     * The same as {@link SomeConfig#getBoolean(String)}
     * but when getBoolean is null it will return the given def value
     *
     * @param path The path in the config
     * @param def The default value if its null
     * @return The value
     */
    public Boolean getBoolean(String path, boolean def) {
        Boolean result = getBoolean(path);
        if (result == null)
            return def;
        else
            return result;
    }

    /**
     * Get a CharSequence between 2 chars ( like 'test' is test )
     *
     * @param message The message you want the String from
     * @param first The start like '
     * @param second The end like *
     * @return a CharSequence like "'example*" is "example"
     */
    public CharSequence stringBetween(String message, String first, String second) {
        Pattern p = Pattern.compile(first + ".*?" + second);
        Matcher m = p.matcher(message);
        if(m.find())
            return m.group().subSequence(1, m.group().length()-1);
        return null;
    }
}
