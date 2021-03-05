### Run program ###
To run program from your computer, click the start.bat. 
This batch locally install apache-ant ant apache-maven on your computer.

### Ensure the correctness ###
After running the program, you will see:
First is initialized
9.63194220096548
Second is initialized
SECOND IS INITIALIZED
Main`s already worked.
MAIN`S ALREADY WORKED.

The same phrases you can see after running each of tree blocks.
Ant and Maven will be accompanied by inscriptions BUILD SUCCESSFULLY.

### Disadvantage of program ###
Description of the batch code.

All temporary environment variables are set directly from start.bat, 
before that I tried the option of creating different baht files to separate tasks 
(installing ant, installing maven, starting ant ...). 
But the setting of the environment variable in a separate batch file 
caused the error "is not an environment variable." Apparently, the main bat file 
calls a third-party bat file, which ends its execution and closes, accordingly not 
letting the system know that the variable can actually be used.


### Testing ###
1. The program was tested from my computer, and the environment variables indicating 
the existence of ANT_HOME and MAVEN were removed from this computer. 
Projects were successfully built.
   
2. Also, the program was tested from a third-party computer with no installation 
at all (only jdk).