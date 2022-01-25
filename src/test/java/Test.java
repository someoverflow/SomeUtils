
import me.someoverflow.someutils.file.SomeFile;

import java.io.IOException;

/**
 * @author SomeOverflow
 */
public class Test {

    public static void main(String[] args) {
        SomeFile file = new SomeFile("test/bug/", "susy.baka");

        try {
            file.override("test1", "test2", "test3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            file.change("test1", "testY");
            file.changeLine(1, "testZ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
