FROM openjdk:11
COPY target/newz-netflix-zuul-api-gateway-0.0.1-SNAPSHOT.jar newz-netflix-zuul-api-gateway-0.0.1.jar
ARG JAR_FILE=target/newz-netflix-zuul-api-gateway-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} newz-netflix-zuul-api-gateway-0.0.1.jar
ENTRYPOINT ["java","-jar","/newz-netflix-zuul-api-gateway-0.0.1.jar"]