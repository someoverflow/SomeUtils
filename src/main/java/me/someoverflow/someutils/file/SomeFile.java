package me.someoverflow.someutils.file;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

/**
 * @author SomeOverflow
 */
@SuppressWarnings("unused")
public class SomeFile {

    private String filePath;
    private String fileName;

    public boolean dirCreated;

    private List<String> defaults;

    public SomeFile(@NotNull String filePath, @NotNull String fileName) {
        this.filePath = filePath;
        File directory = new File(filePath);
        if (!directory.exists()) dirCreated = directory.mkdirs();
        this.fileName = fileName;
    }

    public static final String DESCRIPTION_DEFAULT = "#desc#";
    public static final String PLAIN_DEFAULT = "#plain#";

    /**
     * Set the defaults of the file
     * {@link SomeFile#DESCRIPTION_DEFAULT} will be a description in the config
     *
     * @param defaults A list of the defaults
     */
    public void setDefaults(@NotNull SomeDefaults defaults) {
        this.defaults = new ArrayList<>();
        for (int i = 0; i < defaults.getSize(); i++) {
            String value = defaults.getValue(i);

            String toAdd;

            if (value.equals(DESCRIPTION_DEFAULT))
                this.defaults.add("# " + defaults.getKey(i));
            else if (value.equals(PLAIN_DEFAULT))
                this.defaults.add(defaults.getKey(i));
            else {
                toAdd = defaults.getKey(i) + ": '" + value + "'";
                this.defaults.add(toAdd);
            }
        }
    }

    /**
     * If the file does not exist, it saves the {@link SomeFile#setDefaults(SomeDefaults) given defaults} in the new file
     *
     * @throws IOException See {@link SomeFile#write(String...)}
     */
    public void saveDefaults() throws IOException {
        if (new File(filePath + fileName).exists())
            return;
        for (String s : defaults)
            write(s);
    }

    /**
     * Add Lines to a file, every value String will be separated with a new line
     *
     * @param values The lines to add to the file
     * @throws IOException See {@link BufferedWriter} and {@link FileWriter}
     */
    public void write(@NotNull String... values) throws IOException {
        List<String> toAdd = new ArrayList<>();

        List<String> read = read();
        if (read != null) toAdd.addAll(read);

        toAdd.addAll(Arrays.asList(values));

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + fileName));
        for (int i = 0; i < toAdd.size(); i++) {
            writer.write(
                    toAdd.get(i) +
                    (i == (toAdd.size() - 1) ? "" : "\n")
            );
        }
        writer.close();
    }

    /**
     * Replaces every oldString with the newString in the file
     *
     * @param oldString The old string to change
     * @param newString The new string to change to
     * @throws IOException See {@link SomeFile#override(String...)}
     */
    public void change(@NotNull String oldString, @NotNull String newString) throws IOException {
        StringBuilder fileData = new StringBuilder();
        for (String s : read())
            fileData.append(s).append("\n");
        String res = fileData.toString().replaceAll(oldString, newString);
        override(res);
    }

    /**
     * Changes the specific line to a new string
     * Starting at 0
     *
     * @param line The line which will be changed
     * @param newData The new string it will be changed to
     * @throws IOException See {@link SomeFile#override(String...)}
     */
    public void changeLine(int line, String newData) throws IOException {
        StringBuilder fileData = new StringBuilder();
        int counter = 0;
        for (String data : read()) {
            fileData.append(
                    counter == line ? newData : data
            );

            fileData.append("\n");
            counter++;
        }

        override(fileData.toString());
    }

    /**
     * Override a file, every value String will be separated with a new line
     *
     * @param values The Values to add
     * @throws IOException See {@link BufferedWriter} and {@link FileWriter}
     */
    public void override(String @NotNull ... values) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + fileName));
        if (values.length != 1) {
            for (int i = 0; i < values.length; i++) {
                writer.write(
                        i == (values.length - 1) ?
                                values[i] :
                                values[i] + "\n"
                );
            }
        } else
            writer.write(values[0]);
        writer.close();
    }

    /**
     * Make the file blank
     *
     * @throws IOException See {@link SomeFile#override(String...)}
     */
    @Deprecated
    public void clear() throws IOException {
        override(" ", " ", " ");
    }

    /**
     * Get the lines of the file
     *
     * @return The lines (if null, the file is not existing)
     */
    public List<String> read() {
        if (!new File(filePath + fileName).exists())
            return null;

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
     * Creates a new file and writes in everything of the old one in it
     * Deletes the old one
     *
     * @param newName The new Name
     * @return A List with the keys deleteOldFile (if the old file got deleted) and deleteNewFile (when the new file already existed got deleted)
     * @throws IOException When there is any error while writing to the new File
     */
    public HashMap<String, Object> rename(String newName) throws IOException {
        // Read the data from the old file
        List<String> oValues = read();
        // Delete the old File
        boolean deleteOldFile = new File(filePath + fileName).delete();
        // Delete the new File if it is existing
        boolean deleteNewFile = new File(filePath + newName).delete();

        // Set the new Name
        fileName = newName;

        if (oValues != null) {
            if (oValues.size() != 0)
                for (String value : oValues) write(value);
        }

        HashMap<String, Object> result = new HashMap<>();
        result.put("deleteOldFile", deleteNewFile);
        result.put("deleteNewFile", deleteNewFile);
        return result;
    }

    /**
     * Move a file to a new Directory
     *
     * @param newPath The new Path
     * @return A List with the keys createdDirectory (if the new directory got created) and deletedOldFile (if the old file got deleted)
     * @throws IOException When there is any error while moving to the new path
     */
    public HashMap<String, Object> move(String newPath) throws IOException {
        // Create the new Directory
        boolean createdDirectory = new File(newPath).mkdirs();

        // Read the data from the old file
        List<String > oValues = read();

        // Delete the old File
        boolean deletedOldFile = new File(filePath + fileName).delete();

        // Set the new FilePath
        filePath = newPath;

        // Put the old data in the file
        if (oValues != null) {
            if (oValues.size() != 0)
                for (String value : oValues) write(value);
        }

        HashMap<String, Object> result = new HashMap<>();
        result.put("createdDirectory", createdDirectory);
        result.put("deletedOldFile", deletedOldFile);
        return result;
    }

    /**
     * Delete the file
     *
     * @return If the file is deleted
     */
    public boolean delete() {
        return delete(filePath, fileName);
    }

    /**
     * Delete a file with the given data
     *
     * @param path The path of the File (Should end with / )
     * @param name The name of the File
     * @return If the file got deleted
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