package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import ru.netology.data.DataHelper;
import ru.netology.data.DataHelper.CardInfo;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MoneyTransferTest {
    private static final int AMOUNT = 1_000;
    private final CardInfo firstCard = DataHelper.getFirstCard();
    private final CardInfo secondCard = DataHelper.getSecondCard();
    private DashboardPage dashboard;

    @BeforeAll
    static void configureBrowser() {
        Configuration.baseUrl = "http://localhost:9999";
    }

    @BeforeEach
    void login() {
        dashboard = open("/", LoginPage.class)
                .login(DataHelper.getAuthInfo())
                .verify(DataHelper.getVerificationCode());
    }

    @Test
    @Order(1)
    void shouldTransferMoneyFromSecondToFirstCard() {
        int firstBalance = dashboard.getCardBalance(firstCard);
        int secondBalance = dashboard.getCardBalance(secondCard);

        dashboard = dashboard.selectCardToDeposit(firstCard).transfer(AMOUNT, secondCard);

        assertAll(
                () -> assertEquals(firstBalance + AMOUNT, dashboard.getCardBalance(firstCard)),
                () -> assertEquals(secondBalance - AMOUNT, dashboard.getCardBalance(secondCard))
        );
    }

    @Test
    @Order(2)
    void shouldTransferMoneyFromFirstToSecondCard() {
        int firstBalance = dashboard.getCardBalance(firstCard);
        int secondBalance = dashboard.getCardBalance(secondCard);

        dashboard = dashboard.selectCardToDeposit(secondCard).transfer(AMOUNT, firstCard);

        assertAll(
                () -> assertEquals(firstBalance - AMOUNT, dashboard.getCardBalance(firstCard)),
                () -> assertEquals(secondBalance + AMOUNT, dashboard.getCardBalance(secondCard))
        );
    }
}
