package me.someoverflow.someutils.file;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

/**
 * Useless but gud
 *
 * @author SomeOverflow
 */
public class SomeDefaults {

    private final HashMap<String, Object> content;

    /**
     * List for config defaults
     */
    public SomeDefaults() {
        content = new HashMap<>();
    }

    /**
     * Adds the key with the value to the list
     *
     * @param key The key
     * @param value The value
     * @return The current {@link SomeDefaults}
     */
    public SomeDefaults add(String key, @NotNull Object value) {
        content.put(key, value);
        return this;
    }

    /**
     * Adds a Description
     * Runs {@link SomeDefaults#add(String, Object)} with {@link SomeFile#DESCRIPTION_DEFAULT}
     *
     * @param description The Description
     * @return The current {@link SomeDefaults}
     */
    public SomeDefaults addDescription(String description) {
        return add(description, SomeFile.DESCRIPTION_DEFAULT);
    }

    /**
     * Adds a Plain Line
     * Runs {@link SomeDefaults#add(String, Object)} with {@link SomeFile#PLAIN_DEFAULT}
     *
     * @param plain The Line
     * @return The current {@link SomeDefaults}
     */
    public SomeDefaults addPlain(String plain) {
        return add(plain, SomeFile.PLAIN_DEFAULT);
    }

    /**
     * Creates / Adds a list with the content to the list
     *
     * @param name The name of the list
     * @param value The value of the list
     * @return The current {@link SomeDefaults}
     */
    public SomeDefaults addList(String name, @NotNull List<Object> value) {
        // Add the Name
        addPlain(name + " #{");
        for (Object s : value)
            addPlain("\t- " + s.toString());
        addPlain("}");

        return this;
    }

    /**
     * Returns the key at the given index
     * @param index The index of the key
     * @return The key of the index
     */
    public String getKey(int index) {
        return content.keySet().toArray()[index].toString();
    }

    /**
     * Returns the value of a key at the given index
     * @param index The index
     * @return The value
     */
    public String getValue(int index) {
        return content.values().toArray()[index].toString();
    }

    /**
     * Returns the size of the list
     * @return The size
     */
    public int getSize() {
        return content.size();
    }
}