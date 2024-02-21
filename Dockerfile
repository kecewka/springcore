# Use a base image with Java 11 installed
FROM openjdk:22-ea-17-oracle

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container at /app
COPY target/springcore-0.0.1-SNAPSHOT.jar /app

# Expose the port your application runs on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "springcore-0.0.1-SNAPSHOT.jar"]
