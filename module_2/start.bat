@echo off
@echo .
@echo ---------RUN MAVEN PROGECT ---------
@echo .
@echo ---------Create jar file---------
@echo .
call mvn clean install

@echo ---------Run program---------
@echo .
java -jar target/module_2-1.0-SNAPSHOT.jar
pause