package me.someoverflow.someutils.file;

import java.util.List;

/**
 * Just a List because I am Stoopid
 *
 * @author SomeOverflow
 */
public class SomeDefaults {

    private int size = 0;

    private final String[] keys;
    private final String[] values;

    public SomeDefaults() {
        // I like the number 555 :)
        int MAX_SIZE = 555;
        keys = new String[MAX_SIZE];
        values = new String[MAX_SIZE];
    }

    public void add(String key, String value) {
        keys[size] = key;
        values[size] = value;
        size++;
    }

    public void addList(String name, List<String> value) {
        // Add the Name
        addDes(name + " #{");
        for (String s : value) {
            addDes("\t- " + s);
        }
        addDes("}");
    }

    /**
     * Adds a Description
     * Just runs {@link SomeDefaults#add(String, String)} with {@link SomeFile#DESCRIPTION}
     *
     * @param key The Description
     */
    public void addDes(String key) {
        add(key, SomeFile.DESCRIPTION);
    }

    public String getKey(int index) {
        return keys[index];
    }
    public String getValue(int index) {
        return values[index];
    }

    public int getSize() {
        return size;
    }
}
