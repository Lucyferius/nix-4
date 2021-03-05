@echo off
set ANT_OPTS=-Xmx2G -Dfile.encoding=UTF-8

set ANT_HOME=%~dp0apache_ant
set PATH=%ANT_HOME%\bin;%PATH%
set CLASSPATH=
echo Setting ant home to: %ANT_HOME%
echo ANT_HOME = %ANT_HOME%
pause