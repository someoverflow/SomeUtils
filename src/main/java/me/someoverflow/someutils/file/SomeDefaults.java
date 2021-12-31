package me.someoverflow.someutils.file;

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

    /**
     * Add a Description
     * Just runs {@link SomeDefaults#add(String, String)} with "SomeFile.DESCRIPTION"
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
