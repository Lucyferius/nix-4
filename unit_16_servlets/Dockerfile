FROM tomcat:10.0.7-jdk16-openjdk

COPY target/servlets.war /usr/local/tomcat/webapps/servlets.war

EXPOSE 8080
CMD ["catalina.sh", "run"]





#FROM maven:latest as web
#COPY . /usr/src/webapp
#WORKDIR /usr/src/webapp

#RUN mvn clean install
#
#FROM tomcat:10.0.7-jdk11-openjdk as tomcat
#
#COPY --from=web /usr/src/webapp/target/servlets.war /usr/local/tomcat/webapps/
#
#EXPOSE 8080
#
#CMD ["catalina.sh", "run"]
