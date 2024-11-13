FROM maven:3.8.2-openjdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
COPY Docker/persistence.xml /home/app/main/resources/META-INF/persistence.xml
VOLUME /root/.m2 C:
RUN mvn -X -f /home/app/pom.xml clean package



FROM tomee:jre11-alpine-webprofile AS run
COPY --from=build /home/app/target/ghostnetfishing-1.0.0.war /usr/local/tomee/webapps/GhostNetFishing.war
COPY Docker/Tomee/tomcat-users.xml /usr/local/tomee/conf/tomcat-users.xml
COPY Docker/Tomee/context.xml /usr/local/tomee/conf/context.xml
COPY Docker/Tomee/manager/context.xml /usr/local/tomee/webapps/manager/META-INF/context.xml
