package com.sopehl.util;

import org.apache.maven.monitor.logging.DefaultLog;
import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.logging.console.ConsoleLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessUtils {

    private static final Log LOG = new DefaultLog(new ConsoleLogger());

    public static int execute(String command) {
        String s;
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(command);
            LOG.info("---> Executing: " + command);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null) {
                LOG.info(s);
            }
            p.waitFor();
            LOG.info("~~~> ExitCode: " + p.exitValue());
        } catch (IOException | InterruptedException e) {
            LOG.error(e);
        }

        if (p == null) throw new AssertionError();
        return p.exitValue();
    }

}
