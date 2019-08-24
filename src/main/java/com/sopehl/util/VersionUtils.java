package com.sopehl.util;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

public class VersionUtils {

    private static final Log LOG = new SystemStreamLog();

    private static final String POM_FILE_DIRECTORY = "/META-INF/maven/com.sopehl/fmerger/pom.xml";

    /**
     * <p>Read the version from pom.xml file</p>
     *
     * @return version of plugin like 1.0.0-alpha-1
     */
    public static String getPluginVersion() throws MojoExecutionException {
        MavenXpp3Reader mavenXpp3Reader = new MavenXpp3Reader();
        Model model;
        String modelVersion;
        try {
            InputStream inputStream = FileUtils.class.getResourceAsStream(POM_FILE_DIRECTORY);
            model = mavenXpp3Reader.read(inputStream);
            modelVersion = model.getVersion();
            LOG.debug(String.format("Reading plugin version(%s) from pom file : (%s)", modelVersion, POM_FILE_DIRECTORY));
        } catch (IOException e) {
            throw new MojoExecutionException( "Error reading the pom file : " + e.getMessage(), e );
        } catch (XmlPullParserException e) {
            throw new MojoExecutionException( "Error xml parsing : " + e.getMessage(), e );
        }

        return modelVersion;
    }

}
