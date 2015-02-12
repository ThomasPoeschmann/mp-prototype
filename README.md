mp-prototype
============

In order to run, you should
* install maven (3.2.3 is what I used)
* install JDK (1.7.0_67 for me)
* set some env variables:
JAVA_HOME=c:\Program Files\Java\jdk1.7.0_67
MAVEN_HOME=d:\dev\apache-maven-3.2.3
MAVEN_OPTS=-Xms512m -Xmx1024m -XX:PermSize=256m -XX:MaxPermSize=512m
(the latter is because embedded glassfish needs some memory to operate correctly)
* make sure JAVA_HOME/bin and MAVEN_HOME/bin is in your path
* run "maven clean package" - this will just run the unit tests without glassfish
* run "maven clean install" - this will run the integration tests with embedded glassfish
* RestIntegrationTest just gives you 30 seconds to point your browser to
localhost:8282/rest-demo/app1/test or
http://localhost:8282/rest-demo/app1/test/2/something/3
(you should hurry in order to see it...) 

For Eclipse, I used Eclipse Java EE IDE for Web Developers Version: Kepler Service Release 2
Choose File|Import...|Maven - Existing Maven Projects
Right klick on a test to run it (Run As|Junit Test)

The RestIntegrationTest only runs from the command line, since Glassfish is started there only 
via Maven. 

The EjbIntegrationTest runs in Eclipse as well, since there Glassfish is started programatically.
Please, set a VM parameter "-Dmp-prototype.ejb-demo.earfilename=classes" using Run|Run 
Configurations/Arguments (I can fix this, currently it's a hack).

