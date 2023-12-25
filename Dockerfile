#Build
FROM maven:3.8.3-openjdk-17 AS MAVEN_TOOL_CHAIN
RUN rm -rf ./tmp
COPY pom.xml ./tmp/
COPY src ./tmp/src/
WORKDIR ./tmp/
RUN mvn -P stage clean package -Dmaven.test.skip=true
#Run on tomcat
FROM tomcat:jdk17-openjdk
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=MAVEN_TOOL_CHAIN /tmp/target/weather-forecast-api-0.0.1-SNAPSHOT.war $CATALINA_HOME/webapps/weather-forecast-api.war
CMD ["catalina.sh", "run"]