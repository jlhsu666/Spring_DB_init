# Use Maven image to build the project
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml .
COPY src ./src

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Use a lightweight JDK image for running the application
FROM openjdk:17-jdk-alpine

# Set the working directory for the application
WORKDIR /app

# Copy the JAR file from the previous stage
COPY --from=build /app/target/your-app-name.jar app.jar

# Expose the port Spring Boot will run on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
