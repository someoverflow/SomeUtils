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

        System.out.println(config.getInt("testInt"));
        System.out.println(config.getBoolean("testBool"));
    }

    public static SomeDefaults<String,String> defaults() {
        SomeDefaults<String,String> defaults = new SomeDefaults<>();
        defaults.add("Just a Config", SomeFile.DESCRIPTION);
        defaults.add("SomeOverflow", "cool");
        defaults.add("testInt", "500");
        defaults.add("testBool", "false");
        return defaults;
    }

}
