# 1. Base Image
FROM openjdk:11-jdk-slim

# 2. Application Jar File Path
ARG JAR_FILE=target/*.jar

# 3. Add Jar file to the container
COPY ${JAR_FILE} app.jar

# 4. Run the Jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]
