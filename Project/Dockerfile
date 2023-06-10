FROM openjdk:20

VOLUME /app

EXPOSE 80

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

#новое
CMD ["java", "-jar", "/app.jar"]