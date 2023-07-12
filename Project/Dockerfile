FROM openjdk:17

VOLUME /app

EXPOSE 80

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

CMD ["java", "-jar", "/app.jar"]