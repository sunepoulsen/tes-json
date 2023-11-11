# TES JSON

TES JSON is part of [Tech Easy Solutions](https://sunepoulsen.atlassian.net/wiki/spaces/TES) suite and is provided
to make it easier to work in a common way with JSON in solutions that is based on `Tech Easy Solutions`

It contains commons way to work with JSON and utility classes get going more rapidly.

## Documentation

The documentation of how to use this library can be found the here:

[Documentation](doc/README.md)

## Building

The project is built with Gradle and is requiring Java 17.

The project is integrated with [Sonatype Nexus Repository](https://www.sonatype.com/products/sonatype-nexus-repository) 
and [SonarQube](https://www.sonarsource.com/products/sonarqube/) and is using the following properties from 
`~/.gradle/gradle.properties`:

```properties
# System properties for Nexus

systemProp.maven.repository.base.url=<url to Nexus>
systemProp.maven.repository.snapshots=maven-snapshots
systemProp.maven.repository.releases=maven-releases

systemProp.maven.repository.username=<Username for the account to connect to Nexus>
systemProp.maven.repository.password=<Password for the user account>

# System properties for SonarQube

systemProp.sonar.host.url=<URL to SonarQube>
systemProp.sonar.login=<Auth token to SonarQube>
```

### Pipeline

A complete pipeline to build the project can be used with:

```shell
./pipeline.sh
```

This pipeline has the following steps:

1. Clean the repository.
2. Build the library - including JavaDoc.
3. Analyzing the project with SonarQube - including check of dependency vulnerabilities.
4. Publish the artifacts to Nexus.

The pipeline selects the required Java version to build the project. To get it to work the
developer needs:

1. A working installation of [SDK Man](https://sdkman.io/)
2. Installed the java distribution of `17.0.8.1-tem` with `sdk`. This distribution does not need to be active,
   but it needs to be installed.

If you want to change the Java distribution being used then you can overwrite the variable `JAVA_VERSION` in
`pipeline.sh`

## Code Analysing

The project can be analyzed with [SonarQube](https://www.sonarsource.com/products/sonarqube/) with:

```shell
./gradlew sonar
```

This will also analyze all dependencies for vulnerabilities. This is done with the 
[org.owasp:dependency-check-gradle](https://github.com/dependency-check/dependency-check-gradle) plugin.

Analysing the vulnerabilities without the SonarQube analysis can be done with:

```shell
./gradlew dependencyCheckAnalyze
```

The result of the scan can be found here: `build/reports/dependency-check-*`

## Releasing

The project can be released with Gradle as well. We are using the
[net.researchgate.release](https://github.com/researchgate/gradle-release) to release the project and
can be executed with:

```shell
./gradlew release -Prelease.useAutomaticVersion=true
```

The release process is as follows:

1. The plugin checks for any un-committed files (Added, modified, removed, or un-versioned).
2. Checks for any incoming or outgoing changes.
3. Checkout to the release branch and merge from the working branch (optional, for GIT only, with pushReleaseVersionBranch)
4. Removes the SNAPSHOT flag on your project's version (If used)
5. Automatically update the version with the release version - removing the SNAPSHOT part of the version.
6. Checks if your project is using any SNAPSHOT dependencies
7. Will build your project.
8. Update the CHANGELOG file.
9. Publish the build artifact to Nexus.
10. Check used dependencies for any vulnerabilities with the [org.owasp:dependency-check-gradle](https://github.com/dependency-check/dependency-check-gradle) plugin.
11. Analyze the code with SonarQube. This will also publish any vulnerabilities found by `org.owasp:dependency-check-gradle`
12. Commits the project if SNAPSHOT was being used.
13. Creates a release tag with the current version.
14. Automatically update the version number for the next development cycle.
15. Commits the project with the new version.

