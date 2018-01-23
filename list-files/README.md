# Scans a temp directory, filters *.txt files and sort them by size and name


The project was created in Maven and Java 9. It is integrated with Spring-Core to get the benefits
of Spring annotation and dependency injection.

TestNG has been used to unit test the code. Please open list.files.FileOrchestratorImplTest to go through the different unit test cases.

The JAR was created in the executable mode and can be run by simply typing "java -jar list-files-1.0.0-jar-with-dependencies.jar" in the command line.

The JAR file also takes argument which will be the directory where the scanning will happen. For example; if the command "java -jar list-files-1.0.0-jar-with-dependencies.jar /private/var/folders" is given the code will look into all the *.txt files under the directory /private/var/folders.

If no argument is passed then the code will look into operating system /temp folder and then scan for all the *.txt files.

The package also has source code. Simply unzip list-files-source-code.zip and import the maven project in an IDE. This code was created
using the Eclipse IDE (Oxygen).

Finally, although the code was created on Java 9 it can still be run on Java 8.