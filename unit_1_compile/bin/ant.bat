@echo off
rem call %~dp0bin\config_ant.bat

call ant clean compile jar run 
rem call ant compile
rem call ant jar
rem call ant run
pause