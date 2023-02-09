package me.someoverflow.someutils.file;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SomeOverflow
 */
@SuppressWarnings("unused")
public class SomeConfig {

    private final SomeFile file;
    public SomeConfig(SomeFile file) {
        this.file = file;
    }

    /**
     * Get a specific List from the Config like:
     * list #{
     *     - values
     * }
     *
     * @param path The path in the config
     * @return The value of the List
     */
    public List<String> getList(String path) {
        List<String> result = new ArrayList<>();
        boolean opened = false;
        boolean closed = false;
        for (String s : file.read()) {
            if (s.startsWith(path + " #{") && !opened) opened = true;
            if (opened) {
                if (s.startsWith("}")) {
                    opened = false;
                    closed = true;
                    continue;
                }

                if (s.startsWith("\t- "))
                    result.add(s.replaceFirst("\t- ", ""));
            }
        }

        if (!closed) return null;
        return result;
    }



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
     * (This is in work could make errors please report them)
     * Change a String to a new value
     *
     * @param path The path in the config
     * @param value The new value
     * @throws IOException see {@link SomeFile#change(String, String)}
     */
    public void setString(String path, String value) throws IOException {
        String current = getString(path);
        String toChange = path + ": '" + current + "'";
        String changeTo = path + ": '" + value + "'";

        file.change(toChange, changeTo);
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

        Integer res;
        try { res = Integer.parseInt(result); }
        catch (NumberFormatException ignored) { res = null; }
        return res;
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
        return Objects.requireNonNullElse(result, def);
    }

    /**
     * (This is in work could make errors please report them)
     * Change an Integer to a new value
     *
     * @param path The path in the config
     * @param value The new value
     * @throws IOException see {@link SomeFile#change(String, String)}
     */
    public void setInt(String path, Integer value) throws IOException {
        Integer current = getInt(path);
        String toChange = path + ": '" + current + "'";
        String changeTo = path + ": '" + value + "'";

        file.change(toChange, changeTo);
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

        Double res;
        try { res = Double.parseDouble(result); }
        catch (NumberFormatException ignored) { res = null; }

        return res;
    }
    /**
     * The same as {@link SomeConfig#getDouble(String)}
     * but when getDouble is null it will return the given def value
     *
     * @param path The path in the config
     * @param def The default value if its null
     * @return The value
     */
    public double getDouble(String path, double def) {
        Double result = getDouble(path);
        return Objects.requireNonNullElse(result, def);
    }

    /**
     * (This is in work could make errors please report them)
     * Change a Double to a new value
     *
     * @param path The path in the config
     * @param value The new value
     * @throws IOException see {@link SomeFile#change(String, String)}
     */
    public void setDouble(String path, Double value) throws IOException {
        Double current = getDouble(path);
        String toChange = path + ": '" + current + "'";
        String changeTo = path + ": '" + value + "'";

        file.change(toChange, changeTo);
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
    public boolean getBoolean(String path, boolean def) {
        Boolean result = getBoolean(path);
        return Objects.requireNonNullElse(result, def);
    }

    /**
     * Change a Boolean to a new value
     *
     * @param path The path in the config
     * @param value The new value
     * @throws IOException see {@link SomeFile#change(String, String)}
     */
    public void setBoolean(String path, Boolean value) throws IOException {
        Boolean current = getBoolean(path);
        String toChange = path + ": '" + current + "'";
        String changeTo = path + ": '" + value + "'";

        file.change(toChange, changeTo);
    }

    /* TODO:
    public <T> T get(String path, Class<T> type) {
        return type.parse(getString(path));
    }
    */

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