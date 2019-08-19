package com.sopehl.mojo;

import com.sopehl.util.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.List;

@Mojo(name = "merge")
public class FileMergerMojo extends AbstractMojo {

    @Parameter
    private List<String> paths;

    @Parameter(defaultValue = "src/main/resources/")
    private String resourcePath;

    @Parameter(defaultValue = "=")
    private String contentSeparator;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("FMERGER");

        getLog().info(FileUtils.readFile("banner.txt"));

        if (!resourcePath.endsWith(File.pathSeparator)) {
            String warnMessageFormat = "Please add file separator (%s) to end of resource path on pom.xml";
            getLog().warn(String.format(warnMessageFormat, File.separator));
            resourcePath = resourcePath.concat(File.separator);
        }

        for (String path : paths) {
            List<File> files = FileUtils.listFiles(new File(resourcePath + path));
            for (File item : files) {
                String content = FileUtils.readFile(item);
                System.out.println(content);
            }

            System.out.println(FileUtils.generateContentSeparator(contentSeparator));
        }
    }
}
