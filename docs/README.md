# Описание проекта
## Запуск docker

Ввести команду в терминале docker-compose up;

# Для MYSQL

## Запуск jar файла

Ввести команду в терминале: java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar

## Запуск тестов

Ввести команду в терминале: ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"

# Для Postgresql

## Запуск jar файла
Ввести команду java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar

## Запуск тестов
Ввести команду ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"

## Генерация отчета и автоматическое открытие в браузере по умолчанию

Ввести команду в терминале gradlew allureServe