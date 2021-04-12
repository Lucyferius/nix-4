@echo off
call mvn clean install
cd ./app
java -jar target/unit_6_exception-1.0-SNAPSHOT.jar
pause