@echo off
echo Simple Jar
call "%~dp0bin\compile_from_terminal.bat"

echo.
echo. 





echo Ant Jar
echo. 
cd..
set ANT_OPTS=-Xmx2G -Dfile.encoding=UTF-8

set ANT_HOME=%~dp0resources\apache_ant
set PATH=%ANT_HOME%\bin;%PATH%
set CLASSPATH=

echo Setting ant home to: %ANT_HOME%
echo ANT_HOME = %ANT_HOME%

cd ant_with_libs

call ant clean
call ant compile
call ant jar
call ant run





echo Maven Jar
cd..
set MAVEN_OPT=-Xmx2G -Dfile.encoding=UTF-8

set MAVEN=%~dp0resources\apache-maven
set PATH=%MAVEN%\bin;%PATH%
set CLASSPATH=

echo Setting ant home to: %MAVEN%
echo MAVEN = %MAVEN%

cd maven_with_libs

call mvn archetype:generate -Dfilter=org.example.group:example-artifact
activeMode=false

call mvn clean install

java -jar target/MavenPractice-1.0-SNAPSHOT.jar
pause

