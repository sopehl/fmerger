package com.sopehl.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class FileUtilsTest {

    @Test
    public void readFileTest_success() {
        String banner = FileUtils.readFile("/Users/semihokan/IdeaProjects/fmerger/src/main/resources/banner.txt");
        System.out.println(banner);
        Assert.assertTrue(banner.length() > 0);
    }

    @Test
    public void listFileTest_success() {
        List<File> fileList = FileUtils.listFiles(new File("/Users/semihokan/IdeaProjects/fmerger/src/main/resources"));
        System.out.println(fileList);
        Assert.assertTrue(fileList.size() > 0);
    }
}
