
import me.someoverflow.someutils.log.SomeLogFormatter;
import me.someoverflow.someutils.log.SomeLogger;

/**
 * @author SomeOverflow
 */
public class Test {

    public static void main(String[] args) {
        SomeLogger logger = new SomeLogger("SUS", true, true,
                new SomeLogFormatter.Default(), new SomeLogFormatter.Default(),
                true, "test/", "latest.log");

        logger.info("Damn");
        logger.debug("This");
        logger.warn("Is");
        logger.error("Shit");
    }

}
