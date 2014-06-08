package org.shellty.utils;

import java.io.FileInputStream;
import java.io.IOException;

public class FileUtils {
    public static String readFile(String filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            byte[] buff = new byte[fis.available()];
            fis.read(buff);
            return new String(buff);
        }
    }
}
