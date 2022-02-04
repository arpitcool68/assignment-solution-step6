FROM openjdk:11
COPY target/UserProfileService-0.0.1-SNAPSHOT.jar UserProfileService-0.0.1.jar
ARG JAR_FILE=target/UserProfileService-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} UserProfileService-0.0.1.jar
ENTRYPOINT ["java","-jar","/UserProfileService-0.0.1.jar"]