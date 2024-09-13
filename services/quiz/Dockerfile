# Stage 1: Build the project using Maven
FROM maven:3.8.4-openjdk-17-slim AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application using JRE
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/quiz-0.0.1-SNAPSHOT.jar quiz-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/app/quiz-0.0.1-SNAPSHOT.jar"]
