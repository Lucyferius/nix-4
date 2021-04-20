@echo off
call mvn clean install
java -jar mathset/target/mathset-1.0-SNAPSHOT.jar
pause