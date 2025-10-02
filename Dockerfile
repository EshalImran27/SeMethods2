# Use the official OpenJDK image as the base image
# 'latest' will pull the most recent stable OpenJDK version
FROM openjdk:latest
# Copy the built JAR with dependencies from the local target directory to /tmp in the container
COPY ./target/SeMethods2-0.1.0.2-jar-with-dependencies.jar /tmp
# Set the working directory inside the container
WORKDIR /tmp
# Define the command to run when the container starts
# Runs the JAR file using java -jar
ENTRYPOINT ["java", "-jar", "SeMethods2-0.1.0.2-jar-with-dependencies.jar"]