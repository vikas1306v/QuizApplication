FROM maven:3.6.3-jdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]