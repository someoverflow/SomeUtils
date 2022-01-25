import me.someoverflow.someutils.file.SomeConfig;
import me.someoverflow.someutils.file.SomeDefaults;
import me.someoverflow.someutils.file.SomeFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author SomeOverflow
 */
public class FileExample {

    public static void main(String[] args) {
        // path should have / at the end
        SomeFile file = new SomeFile("path/temp/", "config.example");
        // Set the Defaults
        file.setDefaults(defaults());

        // Generate the File with the Defaults
        try {
            file.saveDefaults();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get the Config from the File
        SomeConfig config = new SomeConfig(file);

        // Get Values
        System.out.println(config.getString("SomeOverflow"));
        System.out.println(config.getBoolean("testBool"));

        // When the testInt is null it will return 20
        System.out.println(config.getInt("testInt", 20));

        for (String values : config.getList("ListExample"))
            System.out.println(values);

        // Rename the file to nConfig.example
        try {
            file.rename("nConfig.example");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Move it out of temp
        try {
            file.move("path/");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Change a line
        file.change("SomeOverflow: 'cool'", "SomeOverflow: 'sus'");
        file.changeLine();
    }

    public static SomeDefaults defaults() {
        SomeDefaults defaults = new SomeDefaults();
        // To add a Description
        defaults.addDes("Just a Config");
        // To add a values
        defaults.add("SomeOverflow", "cool");
        defaults.add("testInt", "500");
        defaults.add("testBool", "false");

        defaults.addList("ListExample",
                new ArrayList<>(Arrays.asList("test1", "test2", "test3", "bread", "tree"))
        );
        return defaults;
    }

}
