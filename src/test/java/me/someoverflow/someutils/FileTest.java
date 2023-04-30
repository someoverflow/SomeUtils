package me.someoverflow.someutils;

import me.someoverflow.someutils.file.SomeConfig;
import me.someoverflow.someutils.file.SomeDefaults;
import me.someoverflow.someutils.file.SomeFile;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("Tests for the file basics (SomeFile, SomeDefaults, SomeConfig)")
public class FileTest {

    SomeFile file;

    @BeforeEach
    @DisplayName("Setup the file for tests")
    void setupFile() {
        // Set up the file and check for path creation
        file = new SomeFile("", "test.file");
        assertFalse(file.dirCreated, "Directory have to be existent because its execution path");
        // Remove the old to test real
        file.delete();
    }

    @Test
    @DisplayName("Test file write, override and read functions")
    void testOverrideWriteRead() {
        List<String> content = new ArrayList<>();
        content.add("Test \tsome stuff yo");
        content.add("");
        content.add("There is a empty line above me");
        content.add("File!");

        assertDoesNotThrow(() -> file.override(content.toArray(String[]::new)));
        assertEquals(content, file.read(), "Should be overridden with the new content");

        String add = "Added content";

        content.add(add);

        assertDoesNotThrow(() -> file.write(add));
        assertEquals(content, file.read(), "Should be added to the file");
    }

    @Test
    @DisplayName("Test file rename and move functions")
    void testRenameMove() {
        // Create the file
        assertFalse(new File(file.getFileName()).exists(), "File should not exist cause it gets deleted on the start of the tests");
        assertDoesNotThrow(() -> file.write("Move Me"), "Add a line to create it");
        assertTrue(new File(file.getFileName()).exists(), "File should exist cause it got written to");

        // Rename the file
        String name = file.getFileName();
        String newName = "moved.file";

        assertDoesNotThrow(() -> file.rename(newName));
        assertSame(newName, file.getFileName(), "File name should now be changed");
        assertTrue(new File(file.getFileName()).exists(), "New file should exist with the right content");
        assertFalse(new File(name).exists(), "The old file should be deleted");

        // Move the file
        String path = file.getFilePath();
        String newPath = "test/";

        assertDoesNotThrow(() -> file.move(newPath));
        assertSame(newPath, file.getFilePath(), "File path should now be changed");
        assertTrue(new File(file.getFilePath() + file.getFileName()).exists(), "New file should exist with the right content");
        assertFalse(new File(path + file.getFileName()).exists(), "The old file should be deleted");

        // Cleanup!
        file.delete();
        new File(file.getFilePath()).delete();
    }

    @Nested
    class DefaultsTests {

        @BeforeEach
        void setupDefaults() {
            file.delete();

            SomeDefaults defaults = new SomeDefaults();
            // To add a Description
            defaults.addDescription("Description");

            // Some stuff
            defaults.add("testString", "string");
            defaults.add("testInt", 1);
            defaults.add("testBool", false);

            defaults.addList("testList",
                    new ArrayList<>(Arrays.asList("test1", "test2", "test3", true, 1))
            );

            file.setDefaults(defaults);
            // Save the file
            assertDoesNotThrow(() -> file.saveDefaults());
        }

        @AfterEach
        void cleanup() {
            file.delete();
        }

        @Nested
        class ConfigTests {
            SomeConfig config;

            @BeforeEach
            void setupConfig() {
                // Get the config from the file
                config = new SomeConfig(file);
                assertNotNull(config);
            }

            @Test
            @DisplayName("Test if the config can read the defaults")
            void testConfigRead() {
                // Check values
                assertEquals("string", config.getString("testString"), "Check if the string is the same as in the default");
                assertEquals(1, config.getInt("testInt"), "Check if the integer is the same as in the default");
                assertEquals(false, config.getBoolean("testBool"), "Check if the boolean is the same as in the default");

                String[] shouldList = new String[] { "test1", "test2", "test3", "true", "1" };
                assertArrayEquals(
                        shouldList,
                        config.getList("testList").toArray(),
                        "Check if the List is the same as in the defaults"
                );
            }

            @Test
            @DisplayName("Test if the values of the config can be changed")
            void testConfigChange() {
                String changeString = "otherTestString";
                int changeInt = 2;
                boolean changeBool = false;

                assertDoesNotThrow(() -> config.setString("testString", changeString));
                assertDoesNotThrow(() -> config.setInt("testInt", changeInt));
                assertDoesNotThrow(() -> config.setBoolean("testBool", changeBool));

                assertEquals(changeString, config.getString("testString"), "Check if the string is the changed one");
                assertEquals(changeInt, config.getInt("testInt"), "Check if the int is the changed one");
                assertEquals(changeBool, config.getBoolean("testBool"), "Check if the boolean is the changed one");
            }
        }
    }
}
