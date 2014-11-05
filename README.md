Corporate POM
=============

#### Purpose

This project defines a set of parent Maven POM files and starters to standardise
and simplify any in-house Java application development. On top of preconfigured
plug-ins and reports relevant to our environments it incorporates Spring Boot.

TODO: Why we should us this project...

#### Usage

In your project POM file `pom.xml` specify the parent POM `hscic-ssd-starter`

    <parent>
        <groupId>uk.gov.hscic.ssd</groupId>
        <artifactId>hscic-ssd-starter</artifactId>
        <version>1.0.0.RELEASE</version>
    </parent>

and if you develop an MVC web application add a dependency on
`hscic-ssd-starter-web`.

    <dependency>
        <groupId>uk.gov.hscic.ssd</groupId>
        <artifactId>hscic-ssd-starter-web</artifactId>
    </dependency>

Spring Boot allows you to embed Apache Tomcat in every artefact it builds.
Ideally, packaging chosen for the project will be JAR archive. This might not be
a desirable behaviour. However, it is more and more considered as a good
practice to provide applications along with a servlet container to minimise
service misconfiguration. If you wish to do so, you can include a *provided*
dependency on `hscic-ssd-starter-tomcat` to build a WAR file that is both
executable and deployable into an external container

    <dependency>
        <groupId>uk.gov.hscic.ssd</groupId>
        <artifactId>hscic-ssd-starter-tomcat</artifactId>
        <scope>provided</scope>
    </dependency>

with `spring-boot-maven-plugin` to automatically attempt to rewrite artefact to
make it executable.

    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>

Versions:

    <spring-boot.version>1.1.6.RELEASE</spring-boot.version>
    <tomcat.version>[8.0.12,8.0.19]</tomcat.version>
    <java.version>1.8</java.version>

Configuration:

    <start-class>uk.gov.hscic.ssd.simple.webapp.Application</start-class>
    <project-name>hscic-ssd-simple-webapp</project-name>

#### Sample POM File

The complete POM file could look like this one:

    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
        <groupId>uk.gov.hscic.ssd</groupId>
        <artifactId>hscic-ssd-simple-webapp</artifactId>
        <version>1.0.0.BUILD-SNAPSHOT</version>
        <packaging>war</packaging>
        <parent>
            <groupId>uk.gov.hscic.ssd</groupId>
            <artifactId>hscic-ssd-starter</artifactId>
            <version>1.0.0.RELEASE</version>
        </parent>
        <properties>
            <spring-boot.version>1.1.6.RELEASE</spring-boot.version>
            <tomcat.version>[8.0.12,8.0.19]</tomcat.version>
            <java.version>1.8</java.version>
            <start-class>uk.gov.hscic.ssd.simple.webapp.Application</start-class>
            <project-name>hscic-ssd-simple-webapp</project-name>
        </properties>
        <dependencies>
            <dependency>
                <groupId>uk.gov.hscic.ssd</groupId>
                <artifactId>hscic-ssd-starter-web</artifactId>
            </dependency>
            <dependency>
                <groupId>uk.gov.hscic.ssd</groupId>
                <artifactId>hscic-ssd-starter-tomcat</artifactId>
            </dependency>
        </dependencies>
        <build>
            <plugins>
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                </plugin>
            </plugins>
        </build>
    </project>

#### Sample Application Class

    @Configuration
    @ComponentScan
    @EnableAutoConfiguration
    public class Application {
        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }
    }

#### Sample REST Controller Class

TODO

#### Starters

TODO

#### Environment Configuration

###### Continuous integration

TODO

Ideally, every project should have assigned its own user credentials to upload
artefacts to a repository manager. This can be achieved by adding the following
section to the configuration file `settings.xml` and creating a corresponding
user in the Nexus server:

    <settings>
        <servers>
            <!-- artefact deployment Nexus server credential (snapshot) -->
            <server>
                <id>${project}-artefact-deployment-snapshot</id>
                <username>${project}-deployment-snapshot</username>
                <password>******</password>
            </server>
            <!-- artefact deployment Nexus server credential (release) -->
            <server>
                <id>${project}-artefact-deployment-release</id>
                <username>${project}-deployment-release</username>
                <password>******</password>
            </server>

###### Artefact repository manager

TODO

Artefact upload should only be performed from the CI server.

#### Maven Commands

To check the configuration:

    mvn help:effective-settings
    mvn help:effective-pom

To package as a Spring Boot executable (subject to correct configuration):

    mvn package

To deploy to the Nexus repository manager (subject to correct configuration):

    mvn deploy

To start the application:

    java -jar -Dspring.profiles.active=dev hscic-ssd-simple-webapp-1.0.0.BUILD-SNAPSHOT.war

#### Links

 - Spring Boot [home page](http://projects.spring.io/spring-boot/)
 - Spring Boot [Maven plug-in](http://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html)
 - Spring Boot [command line parameters](http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)
 - Maven configuration [~\.m2\settings.xml](https://einflivc2/scm/svn/dotfiles)

#### TODO List

###### Project

 - Configure and test `maven-release-plugin`
 - Configure and test code coverage plug-in
 - Configure and test code quality report plug-ins

###### Examples

 - Configure logging library
 - Convert `properties` to `yaml` and define Spring profile along with configuration options, e.g. `server.port`
 - Add REST controller to the current example
 - Build additional more sophisticated example

###### Documentation

 - Describe all starters
 - Document continuous integration server configuration
 - Document artefact repository manager configuration
