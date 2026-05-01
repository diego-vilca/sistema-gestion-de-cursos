# build stage
FROM maven:3.9.15-eclipse-temurin-25-alpine AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# runtime stage
FROM eclipse-temurin:25.0.2_10-jre-ubi10-minimal
WORKDIR /app
COPY --from=builder /app/target/gestion-cursos-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]