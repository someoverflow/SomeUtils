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

    public SomeDefaults add(String key, Object value) {
        keys[size] = key;
        values[size] = value.toString();
        size++;

        return this;
    }

    public SomeDefaults addList(String name, List<Object> value) {
        // Add the Name
        addDes(name + " #{");
        for (Object s : value)
            addDes("\t- " + s.toString());
        addDes("}");

        return this;
    }

    /**
     * Adds a Description
     * Just runs {@link SomeDefaults#add(String, Object)} with {@link SomeFile#DESCRIPTION}
     *
     * @param key The Description
     * @return The current {@link SomeDefaults}
     */
    public SomeDefaults addDes(String key) {
        return add(key, SomeFile.DESCRIPTION);
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
