@echo off
echo UNIT_2_algorithmic_tasks

echo Maven Jar
set MAVEN_OPT=-Xmx2G -Dfile.encoding=UTF-8

set MAVEN=%~dp0libs\apache-maven
set PATH=%MAVEN%\bin;%PATH%
set CLASSPATH=

echo Setting ant home to: %MAVEN%
echo MAVEN = %MAVEN%


call mvn archetype:generate -Dfilter=org.example.group:example-artifact
activeMode=false

call mvn clean install

java -jar target/unit-2-tasks-1.0-SNAPSHOT.jar

pause




