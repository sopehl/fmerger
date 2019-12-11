package com.sopehl.util;

import org.apache.maven.monitor.logging.DefaultLog;
import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.logging.console.ConsoleLogger;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileUtils {

    private static final Integer DEFAULT_CONTENT_SEPARATOR_NUMBER = 20;

    private static final Log LOG = new DefaultLog(new ConsoleLogger());

    public static String readFile(File file){
        String tempData;
        String rawData = "";
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while ((tempData = bufferedReader.readLine()) != null) {
                rawData = rawData.concat(tempData);
                rawData = rawData.concat("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }

                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return rawData;
    }

    public static String readFileAsResourceStream(String resource){
        String val = "";
        String l;
        try {
            InputStream resourceAsStream = FileUtils.class.getResourceAsStream(resource);
            BufferedReader r = new BufferedReader(new InputStreamReader(resourceAsStream));

            while ((l = r.readLine()) != null) {
                val = val.concat(l).concat("\n");
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        return val;
    }

    public static String readFile(String path) {
        return readFile(new File(path));
    }

    public static List<File> listFiles(File file) {
        final File[] files = file.listFiles();

        if (files == null) {
            return Collections.emptyList();
        }

        return Arrays.asList(files);
    }

    public static String generateContentSeparator(String separator) {
        String temp = "";
        for (int i = 0; i < DEFAULT_CONTENT_SEPARATOR_NUMBER; i++) {
            temp = temp.concat(separator);
        }

        return temp;
    }

    public static void write(File file, String content) {
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();

                if (fileWriter != null)
                    fileWriter.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public static boolean moveFile(File source, String where, String newFileName) {
        File target = new File(where);
        where = checkEndOfPath(where);

        if(target.mkdir()){
            LOG.info("Path is created : " + where);
        }

        return source.renameTo(new File(where + newFileName));
    }

    public static String checkEndOfPath(String path) {
        return path.endsWith(File.separator) ? path : path + File.separator;
    }

    public static void cleanAllWorkingTree(List<File> files) {
        files.forEach(file -> {
            try {
                if (!file.getName().equals(".keep")) {
                    final boolean isDeleted = Files.deleteIfExists(file.toPath());
                    LOG.debug(file.getName() + " is deleted : " + isDeleted);
                }
            } catch (IOException e) {
                LOG.error(e);
            }
        });
    }

}
