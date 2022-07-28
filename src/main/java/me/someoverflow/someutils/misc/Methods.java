package me.someoverflow.someutils.misc;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Just a few methods to do some things
 *
 * @author SomeOverflow
 */
public class Methods {

    /**
     * Download a specific file from a source to the target path
     *
     * @param source Where to download the file from
     * @param target The {@link Path} where the file should download to
     * @return The bytes read or written
     * @throws IOException See {@link URL#openStream()} and {@link Files#copy(InputStream, Path, CopyOption...)}
     */
    public static long downloadFile(String source, Path target) throws IOException {
        boolean ignored = target.toFile().mkdirs();

        InputStream in = new URL(source).openStream();
        return Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
    }

}
