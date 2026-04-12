#Start with a base image of jav aruntime
FROM openjdk:21-ea-30-jdk

#app jar
COPY target/loan-service-0.0.1-SNAPSHOT.jar loan-service-0.0.1-SNAPSHOT.jar

ENV SPRING_PROFILES_ACTIVE=stage

#execution of jar file.
ENTRYPOINT ["java", "-jar", "loan-service-0.0.1-SNAPSHOT.jar"]