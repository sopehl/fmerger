package com.sopehl.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GeneratorUtils {

    public static final String DEFAULT_DATE_FORMAT_FOR_GENERATOR = "yyyyMMddHHmmss";

    public static String generateFinalName() {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT_FOR_GENERATOR);
        String finalName = format.format(new Date());
        Random r = new Random();
        int randomInt = r.nextInt(10);
        finalName = finalName.concat("-" + randomInt);

        return finalName;
    }

    public static String generateFinalName(String prefix) {
        boolean disabledSuffix = Boolean.parseBoolean(System.getProperty("disableFinalNameSuffix"));
        if (disabledSuffix) {
            return prefix;
        }

        return prefix + "-" + generateFinalName();
    }

}
