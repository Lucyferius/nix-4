### prepare manifest ###
`$ echo Main-Class: Main>MANIFEST.MF`
### prepare jar output ###
`$ mkdir build\jar`
### run compile ###
`$ javac -sourcepath ./src -d build/classes -cp ./libs/commons-lang3-3.11.jar;./libs/commons-math3-3.6.1.jar src/Other/First.java src/Other/Second.java src/Main.java`
### Run program ###
`$ java -cp build/classes;./libs/commons-lang3-3.11.jar;./libs/commons-math3-3.6.1.jar Main`
