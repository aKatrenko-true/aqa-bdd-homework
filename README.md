# Домашнее задание «BDD»

[![Java CI with Gradle](https://github.com/aKatrenko-true/aqa-bdd-homework/actions/workflows/gradle.yml/badge.svg)](https://github.com/aKatrenko-true/aqa-bdd-homework/actions/workflows/gradle.yml)

Решение задачи №1 с Page Object и необязательной задачи №2 с Cucumber.

## Запуск

Требуются Java 17 и Chrome.

1. Запустить тестовое приложение:

   ```shell
   java -jar artifacts/app-ibank-build-for-testers.jar
   ```

2. В другом терминале запустить Page Object-тесты:

   ```shell
   ./gradlew test
   ```

BDD-сценарий запускается отдельно на только что запущенном приложении, поскольку по условию он проверяет фиксированный итоговый баланс 15 000 рублей:

```shell
./gradlew cucumber
```

## Структура

- `data` — тестовые данные;
- `page` — Page Object-классы страниц входа, проверки, списка карт и перевода;
- `test` — переводы в обе стороны с проверкой изменения балансов;
- `bdd` и `features` — Cucumber steps и Gherkin-сценарий.
