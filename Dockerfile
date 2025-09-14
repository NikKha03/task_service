 # Используем официальный OpenJDK образ для запуска приложения
FROM openjdk:21-jdk-slim

# Указываем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем jar файл из локальной машины в контейнер
COPY target/TaskService-0.0.1-SNAPSHOT.jar TaskService.jar

# Команда для запуска приложения в контейнере
ENTRYPOINT ["java", "-jar", "TaskService.jar"]

# Открываем порт, на котором будет работать приложение
EXPOSE 8080
