package com.sopehl.util;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileUtils {

    public static String readFile(File file){
        String tempData = "";
        String rawData = "";
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((tempData = bufferedReader.readLine()) != null) {
                rawData = rawData.concat(tempData);
                rawData = rawData.concat("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rawData;
    }

    public static String readFile(String path) {
        return readFile(new File(path));
    }

    public static List<File> listFiles(File file) {
        final File[] files = file.listFiles();

        if (files == null) {
            return Collections.emptyList();
        }

        final List<File> fileList = Arrays.asList(files);
        return fileList;
    }

}
