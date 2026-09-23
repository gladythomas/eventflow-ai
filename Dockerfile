# Build stage
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copy Maven wrapper and configuration first
# This allows Docker to cache dependency downloads
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Make Maven wrapper executable and download dependencies
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Copy source code
COPY src/ src/

# Build application
RUN ./mvnw clean package -DskipTests


# Runtime stage
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]