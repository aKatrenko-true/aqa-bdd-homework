package ru.netology.bdd;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.data.DataHelper;
import ru.netology.data.DataHelper.CardInfo;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferSteps {
    private DashboardPage dashboard;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void userIsLoggedIn(String login, String password) {
        Configuration.baseUrl = "http://localhost:9999";
        dashboard = open("/", LoginPage.class)
                .login(new DataHelper.AuthInfo(login, password))
                .verify(DataHelper.getVerificationCode());
    }

    @Когда("пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void userTransfersMoney(int amount, String sourceNumber, int targetCardNumber) {
        CardInfo source = cardByNumber(sourceNumber);
        CardInfo target = cardByOrdinal(targetCardNumber);
        dashboard = dashboard.selectCardToDeposit(target).transfer(amount, source);
    }

    @Тогда("баланс его {int} карты из списка на главной странице должен стать {int} рублей")
    public void cardBalanceShouldBe(int cardNumber, int expectedBalance) {
        assertEquals(expectedBalance, dashboard.getCardBalance(cardByOrdinal(cardNumber)));
    }

    private CardInfo cardByOrdinal(int ordinal) {
        return switch (ordinal) {
            case 1 -> DataHelper.getFirstCard();
            case 2 -> DataHelper.getSecondCard();
            default -> throw new IllegalArgumentException("Неизвестный номер карты: " + ordinal);
        };
    }

    private CardInfo cardByNumber(String number) {
        if (DataHelper.getFirstCard().number().equals(number)) return DataHelper.getFirstCard();
        if (DataHelper.getSecondCard().number().equals(number)) return DataHelper.getSecondCard();
        throw new IllegalArgumentException("Неизвестная карта: " + number);
    }
}
