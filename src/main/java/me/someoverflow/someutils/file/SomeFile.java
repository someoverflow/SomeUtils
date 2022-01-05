package me.someoverflow.someutils.file;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author SomeOverflow
 */
public class SomeFile {

    private final String filePath;
    private String fileName;

    public boolean fileCreated;
    private boolean fileDeleted;

    private List<String> defaults;

    public SomeFile(String filePath, String fileName) {
        this.filePath = filePath;
        File directory = new File(filePath);
        if (!directory.exists()) fileCreated = directory.mkdirs();
        this.fileName = fileName;
    }

    public static final String DESCRIPTION = "#desc#";

    /**
     * Set the defaults of the file
     * SomeFile#DESCRIPTION will be a description in the config
     *
     * @param defaults A "list" of the defaults
     */
    public void setDefaults(SomeDefaults defaults) {
        this.defaults = new ArrayList<>();
        for (int i = 0; i < defaults.getSize(); i++) {
            String value = defaults.getValue(i);
            if (value.equals(DESCRIPTION)) {
                this.defaults.add(defaults.getKey(i));
            } else {
                String toAdd = defaults.getKey(i) + ": '" + value + "'";
                this.defaults.add(toAdd);
            }
        }
    }

    /**
     * If the file does not exist yet, it saves the {@link SomeFile#setDefaults(SomeDefaults) given defaults} in the new file
     * @throws IOException When the {@link SomeFile#write(String...) write function} is throwing something
     */
    public void saveDefaults() throws IOException {
        if (!new File(filePath + fileName).exists()) {
            for (String s : defaults)
                write(s);
        }
    }

    /**
     * Add Lines to a file, every value String will be separated with \n to create a new line
     *
     * @param values The lines to add to the file
     * @throws IOException {@link BufferedWriter} and {@link FileWriter}
     */
    public void write(String... values) throws IOException {
        List<String> toAdd = new ArrayList<>();

        List<String> read = read();
        if (read != null) toAdd.addAll(read);

        toAdd.addAll(Arrays.asList(values));

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + fileName));
        for (String value : toAdd) writer.write(value + "\n");
        writer.close();
    }

    /**
     * Override a file, every value String will be separated with \n to create a new line
     *
     * @param values The Values to add
     * @throws IOException {@link BufferedWriter} and {@link FileWriter}
     */
    public void override(String... values) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + fileName));
        for (String value : values) writer.write(value + "\n");
        writer.close();
    }

    /**
     * To clear the file
     * @throws IOException Look {@link SomeFile#override(String...)}
     */
    public void clear() throws IOException {
        override();
    }

    /**
     * Get the lines of the given file in a String List
     *
     * @return The lines (is null if the file is not existing)
     */
    public List<String> read() {
        if (!new File(filePath + fileName).exists()) return null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath + fileName));
            ArrayList<String> result = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) result.add(line);
            reader.close();
            return result;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Creates a new file and writes in everything that was in the old one
     *
     * @param nName The new Name
     * @throws IOException When there is any error while writing to the new File
     */
    public void rename(String nName) throws IOException {
        String oldName = fileName;
        fileName = nName;
        List<String> oldValues = read();

        fileDeleted = new File(filePath + oldName).delete();
        clear();
        for (String value : oldValues) write(value);
    }

    // TODO: 05.01.2022 Move & Delete

    public String getFileName() {
        return fileName;
    }
    public String getFilePath() {
        return filePath;
    }
}
