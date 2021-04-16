@echo off
call mvn clean install
java -jar app/target/app-1.0-SNAPSHOT.jar
pause