@echo off
@echo .
@echo ---------RUN MAVEN PROGECT ---------
@echo .
@echo ---------Create jar file---------
@echo .
call mvn clean install
cd ./app
@echo .
@echo ---------Run program---------
@echo .
java -jar target/unit_9_ionio_1-1.0-SNAPSHOT.jar
pause