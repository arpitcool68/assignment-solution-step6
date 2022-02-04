FROM openjdk:11
COPY target/NewsService-0.0.1-SNAPSHOT.jar news-service-0.0.1.jar
ARG JAR_FILE=target/NewsService-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} news-service-0.0.1.jar
ENTRYPOINT ["java","-jar","/news-service-0.0.1.jar"]