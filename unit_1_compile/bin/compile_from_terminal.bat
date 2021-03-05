@echo off

cd compile_terminal
REM **********************************
REM создаем папки build/classes
REM **********************************

mkdir build\classes

REM **********************************
REM компилируем .java файлы
REM **********************************

javac -sourcepath .\src -d build\classes -cp .\libs\commons-lang3-3.11.jar;.\libs\commons-math3-3.6.1.jar src\Other\First.java src\Other\Second.java src\Main.java

REM **********************************
REM запуск программы
REM **********************************

java -cp build/classes;./libs/commons-lang3-3.11.jar;./libs/commons-math3-3.6.1.jar Main
