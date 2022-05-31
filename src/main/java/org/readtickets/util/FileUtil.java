package org.readtickets.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    private FileUtil() {
    }

    public static byte[] get(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }
}
