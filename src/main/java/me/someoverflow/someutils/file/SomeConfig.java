package me.someoverflow.someutils.file;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SomeOverflow
 */
public class SomeConfig {

    // TODO: 23.12.2021 Lists

    // TODO: 31.12.2021 Docs what this class is doing

    private final SomeFile file;

    public SomeConfig(SomeFile file) {
        this.file = file;
    }

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
    public String getString(String path, String def) {
        String result = getString(path);
        if (result == null)
            return def;
        else
            return result;
    }
    public Integer getInt(String path) {
        String result = getString(path);
        if (result == null) return null;
        return Integer.parseInt(result);
    }
    public Integer getInt(String path, int def) {
        Integer result = getInt(path);
        if (result == null)
            return def;
        else
            return result;
    }
    public Boolean getBoolean(String path) {
        return Boolean.parseBoolean(getString(path));
    }
    public Boolean getBoolean(String path, boolean def) {
        Boolean result = getBoolean(path);
        if (result == null)
            return def;
        else
            return result;
    }

    public CharSequence stringBetween(String message, String first, String second) {
        Pattern p = Pattern.compile(first + ".*?" + second);
        Matcher m = p.matcher(message);
        if(m.find())
            return m.group().subSequence(1, m.group().length()-1);
        return null;
    }
}
