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
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
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

    public void execute() throws MojoExecutionException {
        String contentSeparatorArgs = System.getProperty("contentSeparator");
        if (StringUtils.isNotEmpty(contentSeparatorArgs)) {
            this.contentSeparator = contentSeparatorArgs;
        }

        getLog().debug("Content separator : " + this.contentSeparator);

        getLog().info(String.format(FileUtils.readFileAsResourceStream("/banner.txt"), VersionUtils.getPluginVersion()));

        if (!resourcePath.endsWith("/")) {
            String warnMessageFormat = "Please add file separator (%s) to end of resource path on pom.xml";
            getLog().warn(String.format(warnMessageFormat, File.separator));
            resourcePath = resourcePath.concat(File.separator);
        }

        String content = "";

        List<File> files = new ArrayList<>();
        for (String path : paths) {
            List<File> filesInPath = FileUtils.listFiles(new File(resourcePath + path));
            files.addAll(filesInPath);
        }

        Comparator<File> timestampComparator = (f1, f2) -> ((Long) f1.lastModified()).compareTo(f2.lastModified());
        files.sort(timestampComparator);

        for (File item : files) {
            content = content.concat(FileUtils.readFile(item)).concat("\n");
        }
        content = content.concat(FileUtils.generateContentSeparator(this.contentSeparator)).concat("\n");

        String finalName = output.getFinalNamePrefix() != null ?
                Generator.generateFinalName(output.getFinalNamePrefix()) : Generator.generateFinalName();

        String extension = output.getExtension();
        String fileNameWithExtension = finalName + "." + extension;
        final String relativePath = output.getPath() + fileNameWithExtension;
        getLog().info("Uploaded merged file : " + relativePath);

        writer = new FileWriter(new File(relativePath));
        writer.write(content);

        boolean printConsole = Boolean.parseBoolean(System.getProperty("console"));
        if (printConsole) {
            getLog().info("Merged Content: \n" + content);
        }

        if (this.archive != null) {
            archiveProvider = new ArchiveProvider(new FileArchive(output.getPath(), archive.getSnapshotPath(), finalName, extension));
            boolean activatedSnapshot = Boolean.parseBoolean(System.getProperty("snapshot"));
            boolean activatedArchive = Boolean.parseBoolean(System.getProperty("archive"));

            if (archive.getSnapshotPath() != null && activatedSnapshot) {
                archiveProvider.snapshot();
            }

            if (archive.getPath() != null && activatedArchive) {
                archiveProvider.archive();
            }
        }
    }
}
