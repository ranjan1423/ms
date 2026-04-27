#Start with a base image of jav aruntime
FROM openjdk:21-ea-30-jdk

#app jar
COPY target/config-server-0.0.1-SNAPSHOT.jar config-server-0.0.1-SNAPSHOT.jar

ENV SPRING_PROFILES_ACTIVE=native

#execution of jar file.
ENTRYPOINT ["java", "-jar", "config-server-0.0.1-SNAPSHOT.jar"]