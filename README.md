# Домашнее задание «BDD»

[![Java CI with Gradle](https://github.com/aKatrenko-true/aqa-bdd-homework/actions/workflows/gradle.yml/badge.svg)](https://github.com/aKatrenko-true/aqa-bdd-homework/actions/workflows/gradle.yml)

Отдельный проект необязательной задачи №2. Сценарий перевода денег реализован в стиле BDD с использованием Cucumber, Gherkin, Page Object и Selenide.

## Запуск

Требуются Java 17 и Chrome.

1. Запустить тестовое приложение:

   ```shell
   java -jar artifacts/app-ibank-build-for-testers.jar
   ```

2. В другом терминале запустить BDD-сценарий:

   ```shell
   ./gradlew test
   ```

## Структура

- `data` — тестовые данные;
- `page` — Page Object-классы;
- `bdd` — Cucumber steps и класс запуска;
- `features` — Gherkin-сценарий перевода 5 000 рублей.
