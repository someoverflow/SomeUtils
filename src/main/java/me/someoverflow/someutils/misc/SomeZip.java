package me.someoverflow.someutils.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Just a class to zip and unzip files
 *
 * @author SomeOverflow
 */
public class SomeZip {

    public static void unZipFile(File fileToUnzip, String output) throws Exception {
        File destDir = new File(output);

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileToUnzip));
        ZipEntry zipEntry = zis.getNextEntry();

        while (zipEntry != null) {
            File newFile = new File(destDir, zipEntry.getName());

            String destDirPath = destDir.getCanonicalPath();
            String destFilePath = newFile.getCanonicalPath();

            if (!destFilePath.startsWith(destDirPath + File.separator)) {
                throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
            }

            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                // fix for Windows-created archives
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }

                // write file content
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        
        zis.closeEntry();
        zis.close();
    }

    /**
     * Zip a source file to the output
     *
     * @param fileToZip The {@link File} which is going to be in the zip
     * @param output The zip file with the source file (should end with .zip)
     * @throws Exception If there is any Exception while zipping
     */
    public static void zFile(File fileToZip, String output) throws Exception {
        // Create the OutputStream for the output zip
        FileOutputStream outputOut = new FileOutputStream(output);
        ZipOutputStream zipOut = new ZipOutputStream(outputOut);

        // Create the InputStream for the file to zip
        FileInputStream fileIn = new FileInputStream(fileToZip);

        // Create ZipEntry
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);

        // Write to the zip
        byte[] bytes = new byte[1024];
        int length;
        while((length = fileIn.read(bytes)) >= 0)
            zipOut.write(bytes, 0, length);

        // Close all Steams
        zipOut.close();
        fileIn.close();
        outputOut.close();
    }

    /**
     * Zip multiple source files to the output
     *
     * @param filesToZip The {@link File} in a {@link List} which is going to be in the zip
     * @param output The zip file with the source file (should end with .zip)
     * @throws Exception If there is any Exception while zipping
     */
    public static void zMFiles(List<File> filesToZip, String output) throws Exception {
        // Create the OutputStream for the output zip
        FileOutputStream outputOut = new FileOutputStream(output);
        ZipOutputStream zipOut = new ZipOutputStream(outputOut);

        // Go through every file to zip and zip it
        for (File fileToZip : filesToZip) {
            // Create the InputStream for the file
            FileInputStream fis = new FileInputStream(fileToZip);

            // Create the ZipEntry
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            // Write to the zip
            byte[] bytes = new byte[1024];
            int length;
            while((length = fis.read(bytes)) >= 0)
                zipOut.write(bytes, 0, length);

            // Close Steam
            fis.close();
        }

        // Close the Output Streams
        zipOut.close();
        outputOut.close();
    }

}
