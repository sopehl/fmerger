package com.sopehl.mojo;

import com.sopehl.impl.FileWriter;
import com.sopehl.model.Output;
import com.sopehl.spec.Writeable;
import com.sopehl.util.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.Date;
import java.util.List;

@Mojo(name = "merge")
public class FileMergerMojo extends AbstractMojo {

    @Parameter
    private List<String> paths;

    @Parameter(defaultValue = "src/main/resources/")
    private String resourcePath;

    @Parameter(defaultValue = "=")
    private String contentSeparator;

    @Parameter
    private Output output;

    private Writeable writer;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("FMERGER is started. Good Luck! :)");

        getLog().info(FileUtils.readFileAsResourceStream("/banner.txt"));

        if (!resourcePath.endsWith(File.separator)) {
            String warnMessageFormat = "Please add file separator (%s) to end of resource path on pom.xml";
            getLog().warn(String.format(warnMessageFormat, File.separator));
            resourcePath = resourcePath.concat(File.separator);
        }

        String content = "";

        for (String path : paths) {
            List<File> files = FileUtils.listFiles(new File(resourcePath + path));
            for (File item : files) {
                content = content.concat(FileUtils.readFile(item)).concat("\n");
            }

            content = content.concat(FileUtils.generateContentSeparator(contentSeparator)).concat("\n");
        }

        writer = new FileWriter(new File(output.getPath() + "myFmerger." + output.getExtension()));
        writer.write(content);
    }
}
