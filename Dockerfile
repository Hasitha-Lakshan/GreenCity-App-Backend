FROM openjdk:8-jre-alpine
WORKDIR /app
COPY target/green-city-app-backend.jar /app
EXPOSE 8080
CMD ["java", "-jar", "/app/green-city-app-backend.jar"]
