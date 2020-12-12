package com.sopehl.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class FileUtilsTest {

    private final String testFileName = "banner.txt";
    private final URL testFileUrl = FileUtilsTest.class.getClassLoader().getResource(testFileName);
    private final String resourceAbsolutePath = Objects.requireNonNull(testFileUrl).getPath().split("/" + testFileName)[0];

    @Test
    public void readFileTest_success() {
        assert testFileUrl != null;
        String banner = FileUtils.readFile(testFileUrl.getPath());
        System.out.println(banner);
        Assert.assertTrue(banner.length() > 0);
    }

    @Test
    public void listFileTest_success() {
        List<File> fileList = FileUtils.listFiles(new File(resourceAbsolutePath));
        System.out.println(fileList);
        Assert.assertTrue(fileList.size() > 0);
    }
}
