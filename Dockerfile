# Build stage
FROM eclipse-temurin:21-jdk as build
WORKDIR /app
COPY . .
RUN ./gradlew clean build --no-daemon

# Run stage
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
