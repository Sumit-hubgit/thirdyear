# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /thirdyear# Copy the pom.xml to download dependencies
RUN mvn dependency:go-offline -B 
COPY src ./src      
RUN mvn clean package -DskipTests 

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /thirdyear
COPY --from=build /thirdyear/target/thirdyear-0.0.1-SNAPSHOT.jar thirdyear.jar 
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "thirdyear.jar"]

