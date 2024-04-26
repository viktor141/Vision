package com.cifrazia.vision;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class PropertiesEnvironment {
    public static String VERSION;

    public static void main(String[] args) {
        String filePath = "gradle.properties";
        appendProperty(filePath, "version");
    }

    public static void appendProperty(String filePath, String key) {
        try {
            // Read all lines of the file into a List
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            // Optionally, add an empty line for separation
            lines.add("");

            String newVersion = "0.0.1";
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith(key)) {
                    String[] oldVersion = lines.get(i).split("=")[1].split("\\.");
                    int num = Integer.parseInt(oldVersion[2]);
                    num++;

                    newVersion = oldVersion[0] + "." + oldVersion[1] + "." + num;
                    lines.set(i, key + "=" + newVersion);
                }
            }
            VERSION = newVersion;

            // Write the lines back to the file, preserving existing content and formatting
            Files.write(Paths.get(filePath), lines, StandardOpenOption.WRITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
