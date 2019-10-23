package com.sopehl.mojo;

import com.sopehl.impl.ArchiveProvider;
import com.sopehl.impl.FileArchive;
import com.sopehl.impl.FileWriter;
import com.sopehl.model.Archive;
import com.sopehl.model.Output;
import com.sopehl.spec.Writeable;
import com.sopehl.util.FileUtils;
import com.sopehl.util.Generator;
import com.sopehl.util.VersionUtils;
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

    @Parameter
    private Output output;

    @Parameter
    private Archive archive = null;

    private Writeable writer;

    private ArchiveProvider archiveProvider;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info(String.format(FileUtils.readFileAsResourceStream("/banner.txt"), VersionUtils.getPluginVersion()));

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

        String finalName = output.getFinalName() != null ?
                Generator.generateFinalName(output.getFinalName()) : Generator.generateFinalName();

        String extension = output.getExtension();
        String fileNameWithExtension = finalName + "." + extension;
        final String relativePath = output.getPath() + fileNameWithExtension;
        getLog().info("Uploaded merged file : " + relativePath);

        writer = new FileWriter(new File(relativePath));
        writer.write(content);

        if (this.archive != null) {
            archiveProvider = new ArchiveProvider(new FileArchive(output.getPath(), archive.getSnapshotPath(), finalName, extension));
            //String snapshotParam = System.getProperty("snapshot");
            if (archive.getSnapshotPath() != null) {
                archiveProvider.snapshot();
            }

            if (archive.getPath() != null) {
                archiveProvider.archive();
            }
        }
    }
}
