FROM openjdk:11-jdk
#Основа 11 Java
ARG JAR_FILE=build/libs/main-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]