import me.someoverflow.someutils.file.SomeConfig;
import me.someoverflow.someutils.file.SomeDefaults;
import me.someoverflow.someutils.file.SomeFile;

import java.io.IOException;

/**
 * @author SomeOverflow
 */
public class Test {

    public static void main(String[] args) {
        SomeFile file = new SomeFile("sometemp/", "file.swf-temp");
        SomeDefaults defaults = new SomeDefaults();
        defaults.add("| =========+========= |", SomeFile.DESCRIPTION);
        defaults.add("|   TEST   - TESTTEMP |", SomeFile.DESCRIPTION);
        defaults.add("| =========+========= |", SomeFile.DESCRIPTION);
        defaults.add("", SomeFile.DESCRIPTION);
        defaults.add("prefix", "&8[&3Jobs&8] &7: ");
        defaults.add("", SomeFile.DESCRIPTION);
        defaults.add("debug", "false");
        file.setDefaults(defaults);
        try {
            file.saveDefaults();
        } catch (IOException e) {
            System.out.println("Error saving the config");
        }
        SomeConfig config = new SomeConfig(file);
        System.out.println(config.getString("prefix") + "");
        System.out.println(config.getBoolean("debug") + "");
    }

}
