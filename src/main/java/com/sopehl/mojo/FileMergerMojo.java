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

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("FMERGER");

        String banner = FileUtils.readFile("/Users/semihokan/IdeaProjects/fmerger/src/main/resources/banner.txt");
        getLog().info(banner);

        if (!resourcePath.endsWith(File.pathSeparator)) {
            getLog().warn("Please add file separator to end of resource path on pom.xml");
            resourcePath = resourcePath.concat(File.pathSeparator);
        }

        for (String path : paths) {
            System.out.println(FileUtils.listFiles(new File(resourcePath + path)));
        }
    }
}
