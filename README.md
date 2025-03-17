# Rate Converter

## Описание
Этот сервис конвертирует валюты по текущим курсам, используя API **Coinbase**.

## Запуск в Docker
```sh
docker-compose up --build
```

## Запуск вручную
```sh
./gradlew clean build
java -jar build/libs/rate-converter-0.0.1-SNAPSHOT.jar
```
Swagger доступен по адресу `http://localhost:8080/swagger-ui.html`


