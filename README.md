# HandsApp API
API для проекта HandsApp (https://github.com/aspmap/HandsApp)

**Старт проекта:** 7 мая 2025 г.

:computer: **Требования к софту:**
1. Spring Boot
2. Gradle 8.13
4. Java 17.0.8
5. PostgreSQL 12

:white_check_mark: **Что реализовано:**
1. Получение JWT-токена POST http://localhost:8080/token
2. Авторизация и аутентификация пользователя с помощью полученного (в п.1) токена GET http://localhost:8080/
3. Получение поста с помощью полученного (в п.1) токена GET http://localhost:8080/post/{id}
4. Простые Unit-тесты 
5. Получение JWT-токена через фронт на ReactJS 
6. Авторизация и аутентификация пользователя с помощью полученного (в п.1) токена через фронт на ReactJS 
7. Получение всех постов с помощью полученного (в п.1) токена через фронт на ReactJS

:abcd: **REST-API:**

Используется JWT-токен

> Выборка всех постов

GET http://localhost:8080/posts



