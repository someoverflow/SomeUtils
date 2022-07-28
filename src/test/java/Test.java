
import me.someoverflow.someutils.file.SomeFile;

import java.io.IOException;

/**
 * @author SomeOverflow
 */
public class Test {

    public static void main(String[] args) {
        SomeFile file = new SomeFile(System.getProperty("user.dir"), "/test.test");

        try {
            file.override("test1", "test2", "test3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            file.write("Test", "Test", "Test");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
