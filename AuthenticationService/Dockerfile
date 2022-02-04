FROM openjdk:11
COPY target/AuthenticationService-0.0.1-SNAPSHOT.jar auth-service-0.0.1.jar
ARG JAR_FILE=target/AuthenticationService-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} auth-service-0.0.1.jar
ENTRYPOINT ["java","-jar","/auth-service-0.0.1.jar"]