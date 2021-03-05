@echo off
call %~dp0bin\config_ant.bat

cd ant_with_libs
call ant clean
call ant compile
call ant jar
call ant run
pause