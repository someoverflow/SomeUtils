package me.someoverflow.someutils.file;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author SomeOverflow
 */
public class SomeFile {

    private String filePath;
    private String fileName;

    public boolean fileCreated;

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
     * Changes every oldLine to the newLine parameter
     *
     * @param oldLine The old line to change
     * @param newLine The new line to change to
     * @throws IOException see {@link SomeFile#override(String...)}
     */
    public void change(String oldLine, String newLine) throws IOException {
        StringBuilder fileData = new StringBuilder();
        for (String s : read())
            fileData.append(s).append("\n");
        String res = fileData.toString().replace(oldLine, newLine);
        override(res);
    }

    /**
     * Changes the specific line to the newLine parameter
     * The first line is 0 and so on
     *
     * @param line The line which will be changed
     * @param newLine The new line it will be changed to
     * @throws IOException see {@link SomeFile#override(String...)}
     */
    public void changeLine(int line, String newLine) throws IOException {
        StringBuilder fileData = new StringBuilder();
        int counter = 0;
        for (String data : read()) {
            if (counter == line) fileData.append(newLine);
            else fileData.append(data);
            fileData.append("\n");
            counter++;
        }
        override(fileData.toString());
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
     * To clear the file (Could be make Errors)
     * @throws IOException Look {@link SomeFile#override(String...)}
     */
    @Deprecated
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
        // Read the data from the old file
        List<String> oValues = read();
        // Delete the old File
        new File(filePath + fileName).delete();
        // Delete the new File if it is existing
        new File(filePath + nName).delete();

        // Set the new Name
        fileName = nName;

        if (oValues != null) {
            if (oValues.size() != 0)
                for (String value : oValues) write(value);
        }
    }

    /**
     * Move a file to a new Directory
     * @param nPath The new Path
     * @throws IOException When there is any error while moving to the new path
     */
    public void move(String nPath) throws IOException {
        // Create the new Directory
        new File(nPath).mkdirs();

        // Read the data from the old file
        List<String > oValues = read();

        // Delete the old File
        new File(filePath + fileName).delete();

        // Set the new FilePath
        filePath = nPath;

        // Put the old data in the file
        if (oValues != null) {
            if (oValues.size() != 0)
                for (String value : oValues) write(value);
        }
    }

    /**
     * Delete the current file
     *
     * @return If the file is deleted
     */
    public boolean delete() {
        return delete(filePath, fileName);
    }

    /**
     * Delete a File with the given data
     *
     * @param path The path of the File (Should end with / )
     * @param name The name of the File
     * @return If the file is deleted
     */
    public boolean delete(String path, String name) {
        return new File(path + name).delete();
    }

    public String getFileName() {
        return fileName;
    }
    public String getFilePath() {
        return filePath;
    }
}
