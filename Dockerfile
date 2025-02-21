# Use an official Maven + Java 17 image as the base
FROM maven:3.9.0-eclipse-temurin-17

# Set the working directory in the container
WORKDIR /app

# Copy only the pom.xml first and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of your project files
COPY . .

# By default, run your test suite using the testng.xml file
CMD ["mvn", "clean", "test", "-DxmlName=testng.xml"]
