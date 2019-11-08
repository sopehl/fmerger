   _______ \.___  ___\.  _______ \.______        _______  _______ \.______
  |   ____||   \/   | |   ____||   _  \      /  _____||   ____||   _  \
  |  |__   |  \  /  | |  |__   |  |_)  |    |  |  __  |  |__   |  |_)  |
  |   __|  |  |\/|  | |   __|  |      /     |  | |_ | |   __|  |      /
  |  |     |  |  |  | |  |____ |  |\  \\----\.|  |__| | |  |____ |  |\  \\----\.
  |__|     |__|  |__| |_______|| _| `._____| \______| |_______|| _| `._____|

## Getting Started
Fmerger is a plugin that you can merge all files configured in basic Java application. There can be many
use case like merging the all .sql files in only single output file that you name with specific file name.
Fmerger looks for files in classpath and that classpath path can be configured in pom.xml file. The
specific file group separator can be configured, if it is became involved in configured the default separator '-'. The all configuration elements were explained in configuration section.

### Requirement

	java_version >= 1.8
	maven_version >= 3.3.1

### Configurations

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

                    <paths>
                        <param>okan.pehlivan</param>
                        <param>isah.bllaca</param>
                        <param>dummy.developer</param>
                    </paths>

                    <contentSeparator>--</contentSeparator>

                    <output>
                        <protocol>file</protocol>
                        <finalName>paratika</finalName>
                        <path>/Users/semihokan/fmerger-collection</path>
                        <extension>sql</extension>
                    </output>

                    <archive>
                        <path>/Users/semihokan/fmerger/archive</path>
                        <protocol>file</protocol>
                        <snapshotPath>/Users/semihokan/fmerger/snapshot</snapshotPath>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>

```