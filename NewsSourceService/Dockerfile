FROM openjdk:11
COPY target/NewsSourceService-0.0.1-SNAPSHOT.jar news-source-service-0.0.1.jar
ARG JAR_FILE=target/NewsSourceService-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} news-source-service-0.0.1.jar
ENTRYPOINT ["java","-jar","/news-source-service-0.0.1.jar"]