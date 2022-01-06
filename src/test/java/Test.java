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

    public static void main(String[] args) {
        SomeFile file = new SomeFile("test/", "test.test");

        SomeDefaults defaults = new SomeDefaults();
        defaults.addDes("TEST ___ ");
        defaults.add("Test1", "beeeeeans");
        defaults.addDes("TEST --- ");

        defaults.addList("test", new ArrayList<>(Arrays.asList("Test1", "Test2", "Test3")));

        file.setDefaults(defaults);
        try {
            file.saveDefaults();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SomeConfig config = new SomeConfig(file);

        System.out.println(config.getString("Test1"));

        for (String list : config.getList("test"))
            System.out.println(list + " ");

        try {
            file.rename("rTest.test");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(config.getString("Test1"));

        try {
            file.move("test/temp/");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(config.getString("Test1"));
    }

}
