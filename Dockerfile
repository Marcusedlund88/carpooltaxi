FROM openjdk:17
VOLUME /tmp
ARG JAR_FILE=build/libs/carpooltaxi-0.0.1-SNAPSHOT.jar.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]