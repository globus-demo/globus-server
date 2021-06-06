# globus-server
Server for globus app
#Запуск
1. Установить PostgreSQL создать юзера и базу данных 
2. Задать юзера и пароль в приложении в файле ресурса
```
spring.datasource.url=jdbc:postgresql://localhost:5432/ИМЯ_БАЗЫ_ДАННЫХ
spring.datasource.username=ИМЯ_ЮЗЕРА
spring.datasource.password=ПАРОЛЬ
```
Запустить проложение.
