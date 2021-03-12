@echo off
echo Simple Jar
call "%~dp0bin\compile_from_terminal.bat"

echo.
echo. 

cd

cd ..
cd
cd .\resources

cd
call config_ant.bat
cd..
cd .\ant_with_libs
call ant.bat


rem echo Ant Jar
rem echo. 
rem cd..
rem et ANT_OPTS=-Xmx2G -Dfile.encoding=UTF-8

rem set ANT_HOME=%~dp0resources\apache_ant
rem set PATH=%ANT_HOME%\bin;%PATH%
rem set CLASSPATH=

rem echo Setting ant home to: %ANT_HOME%
rem echo ANT_HOME = %ANT_HOME%

rem cd ant_with_libs

rem call ant clean
rem call ant compile
rem call ant jar
rem call ant run

pause




