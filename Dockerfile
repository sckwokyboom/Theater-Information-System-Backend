# Первый этап: сборка JAR-файла
FROM gradle:8.5.0-jdk21 AS builder
WORKDIR /app
COPY build.gradle.kts settings.gradle.kts ./
COPY src ./src
RUN gradle build -x test

# Второй этап: создание образа
FROM openjdk:21-jdk
WORKDIR /app
COPY --from=builder /app/build/libs/Theater-Information-System-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
