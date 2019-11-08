```
 _______ .___  ___.  _______ .______        _______  _______ .______
|   ____||   \/   | |   ____||   _  \      /  _____||   ____||   _  \
|  |__   |  \  /  | |  |__   |  |_)  |    |  |  __  |  |__   |  |_)  |
|   __|  |  |\/|  | |   __|  |      /     |  | |_ | |   __|  |      /
|  |     |  |  |  | |  |____ |  |\  \----.|  |__| | |  |____ |  |\  \----.
|__|     |__|  |__| |_______|| _| `._____| \______| |_______|| _| `._____|

```

## Getting Started
Fmerger is a plugin that you can merge all files configured in basic Java application. There can be many
use case like merging the all .sql files in only single output file that you name with specific file name.
Fmerger looks for files in classpath and that classpath path can be configured in pom.xml file. The
specific file group separator can be configured, if it is became involved in configured the default separator '-'. The all configuration elements were explained in configuration section.

### Requirement

	java_version >= 1.8
	maven_version >= 3.3.1

### Configurations

The all piece of fmerger plugin will be analyzed step by step and at the end of this section, you can see the big picture of your build tag in your pom.xml file.

---

Path of all collected resources(parent) 
```xml
<resourcePath>src/main/resources/</resourcePath>
```

---

File group content separator configured with twise minus, default is single minus. 
```xml
<contentSeparator>--</contentSeparator>
```

---

All folders that are collected in ***resourcePath*** folder that is configured previously. Maybe those paths can be configured with the developers name or nickname etc.
***Note:*** That mechanism cannot work like recursive so it cannot find the inner folders.
Your java app side will be like:

```
.
+-- src
|   +-- main
|   	+-- resources
|   		+-- okan.pehlivan
|   			+-- test.sql
|   			+-- another-test.sql
|   		+-- other.developer
|   			+-- developer-test.sql

```

```xml
<paths>
	<param>okan.pehlivan</param>
    <param>other.developer</param>
</paths>
```

---

The following configuration tags for all-in-one merged file outputs. It contains protocol(file directory), finalName(prefix of output file), 
path(last path to upload output file) and extension of file like .sql, .txt etc.

Note: The output file name will contains the timestamp and random number. The output file template seems like:

	${output.finalName}-<timestamp>-<randomNumber>-${output.extension}

```xml
<output>
    <protocol>file</protocol>
    <finalName>my-file</finalName>
    <path>/Users/semihokan/fmerger-collection</path>
    <extension>sql</extension>
</output>
```

---

The following config for archive configuration. When the argument is written to console as following: 

	-Darchive

It will create a new archive file if not existed in your file directory. And the last output file will be created in there and in ${output.path} also. Archive will clear all files that you worked on. Be carefull when you use it, but if you made a mistake check the archive folder, for the long files maybe you cannot seperate the file like previously. If you don't want to do that mistake, the snapshotPath config can be used before archive(release) to check the last output file what looks like, by using the following argument:

	-Dsnapshot

Note: Your snapshot output file will be marked with ***-SNAPSHOT*** suffix.

```xml
<archive>
    <path>/Users/semihokan/fmerger-collection/archive</path>
    <protocol>file</protocol>
    <snapshotPath>/Users/semihokan/fmerger-collection/snapshot</snapshotPath>
</archive>
```

---
***Last presentation of build tag in your pom.xml file of your java application.***

```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.sopehl</groupId>
            <artifactId>fmerger</artifactId>
            <version>1.0.1-alpha</version>
            <executions>
                <execution>
                    <phase>generate-resources</phase>
                    <goals>
                        <goal>merge</goal>
                    </goals>
                </execution>
            </executions>

            <configuration>
                <resourcePath>src/main/resources/</resourcePath>

                <contentSeparator>---</contentSeparator>

                <paths>
                    <param>okan.pehlivan</param>
                    <param>other.developer</param>
                </paths>

                <output>
                    <protocol>file</protocol>
                    <finalName>my-file</finalName>
                    <path>/Users/semihokan/fmerger-collection</path>
                    <extension>sql</extension>
                </output>

                <archive>
                    <path>/Users/semihokan/fmerger-collection/archive</path>
                    <protocol>file</protocol>
                    <snapshotPath>/Users/semihokan/fmerger-collection/snapshot</snapshotPath>
                </archive>
            </configuration>
        </plugin>
    </plugins>
</build>

```