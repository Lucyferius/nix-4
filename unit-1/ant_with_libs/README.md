
D:\NIX-4\unit-1\ant_with_libs>ant classpath
Buildfile: D:\NIX-4\unit-1\ant_with_libs\build.xml

BUILD FAILED
Target "classpath" does not exist in the project "null".

Total time: 0 seconds

D:\NIX-4\unit-1\ant_with_libs>ant clean
Buildfile: D:\NIX-4\unit-1\ant_with_libs\build.xml

clean:

BUILD SUCCESSFUL
Total time: 0 seconds

D:\NIX-4\unit-1\ant_with_libs>ant compile
Buildfile: D:\NIX-4\unit-1\ant_with_libs\build.xml

compile:
    [mkdir] Created dir: D:\NIX-4\unit-1\ant_with_libs\build\classes
    [javac] Compiling 3 source files to D:\NIX-4\unit-1\ant_with_libs\build\classes

BUILD SUCCESSFUL
Total time: 0 seconds

D:\NIX-4\unit-1\ant_with_libs>ant jar
Buildfile: D:\NIX-4\unit-1\ant_with_libs\build.xml

compile:

jar:
    [mkdir] Created dir: D:\NIX-4\unit-1\ant_with_libs\build\jar
      [jar] Building jar: D:\NIX-4\unit-1\ant_with_libs\build\jar\main.jar

BUILD SUCCESSFUL
Total time: 1 second

D:\NIX-4\unit-1\ant_with_libs>ant run
Buildfile: D:\NIX-4\unit-1\ant_with_libs\build.xml

run:
     [java] First is initialized
     [java] 12.375062016405373
     [java] Second is initialized
     [java] SECOND IS INITIALIZED
     [java] Main`s already worked.
     [java] MAIN`S ALREADY WORKED.

BUILD SUCCESSFUL
Total time: 0 seconds