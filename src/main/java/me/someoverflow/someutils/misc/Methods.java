package me.someoverflow.someutils.misc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
@SuppressWarnings("unused")
public class Methods {

    /**
     * Download a specific file from a source to the target path
     *
     * @param source Where to download the file from
     * @param target The {@link Path path} where the file should download to
     * @return See {@link Files#copy(InputStream, Path, CopyOption...)} using {@link StandardCopyOption#REPLACE_EXISTING}
     * @throws IOException See {@link URL#openStream()} and {@link Files#copy(InputStream, Path, CopyOption...)}
     */
    public static long downloadFile(String source, Path target) throws IOException {
        boolean ignored = target.toFile().mkdirs();

        InputStream in = new URL(source).openStream();
        return Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
    }

}