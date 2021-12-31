import me.someoverflow.someutils.file.SomeConfig;
import me.someoverflow.someutils.file.SomeDefaults;
import me.someoverflow.someutils.file.SomeFile;

import java.io.IOException;

/**
 * @author SomeOverflow
 */
public class FileExample {

    public static void main(String[] args) {
        // path should have / at the end
        SomeFile file = new SomeFile("path/", "config.example");
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
    }

    public static SomeDefaults defaults() {
        SomeDefaults defaults = new SomeDefaults();
        // To add a Description
        defaults.addDes("Just a Config");
        // To add a values
        defaults.add("SomeOverflow", "cool");
        defaults.add("testInt", "500");
        defaults.add("testBool", "false");
        return defaults;
    }

}
