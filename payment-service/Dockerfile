#FROM eclipse-temurin:17-jdk as build
#WORKDIR /workspace
#COPY . /workspace
#RUN ./mvnw -DskipTests package
#
#FROM eclipse-temurin:17-jre
#WORKDIR /app
#COPY --from=build /workspace/target/payment-service-1.0-SNAPSHOT.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app/app.jar"]
# ---------- Stage 1: Build ----------
FROM eclipse-temurin:17-jdk AS build

WORKDIR /workspace/app

# Copy Maven config and source code
COPY pom.xml .
COPY src ./src

# Install Maven if not present in image
RUN apt-get update && apt-get install -y maven

# Build Spring Boot executable JAR
RUN mvn clean package -DskipTests

# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /workspace/app/target/payment-service-1.0-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
