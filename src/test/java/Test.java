import me.someoverflow.someutils.file.SomeConfig;
import me.someoverflow.someutils.file.SomeDefaults;
import me.someoverflow.someutils.file.SomeFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author SomeOverflow
 */
public class Test {

    public static void main(String[] args) throws IOException {
        SomeFile file = new SomeFile("test/", "test.test");

        SomeDefaults defaults = new SomeDefaults();
        defaults.addDes("TEST ___ ");
        defaults.add("Test1", "beeeeeans");
        defaults.addDes("TEST --- ");

        defaults.addList("test", new ArrayList<>(Arrays.asList("Test1", "Test2", "Test3")));

        file.setDefaults(defaults);
        file.saveDefaults();

        SomeConfig config = new SomeConfig(file);

        System.out.println(config.getString("Test1"));
        System.out.println(config.getInt("Test1") + " ");
        System.out.println(config.getDouble("Test1") + " ");
        System.out.println(config.getBoolean("Test1") + " ");

        for (String list : config.getList("test"))
            System.out.println(list + " ");

        file.rename("rTest.test");
    }

}
