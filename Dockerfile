# Use a base image with Java runtime
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /springApi

# Copy the JAR file into the container's working directory
COPY target/springRestApi-0.0.1-SNAPSHOT.jar /springApi/

# Expose the port your application listens on (e.g., 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "springRestApi-0.0.1-SNAPSHOT.jar"]
