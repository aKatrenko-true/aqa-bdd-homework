package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper.CardInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private static final Pattern BALANCE_PATTERN = Pattern.compile("баланс: (\\d+) р\\.");

    public DashboardPage() {
        $("[data-test-id=dashboard]").shouldBe(visible);
    }

    public int getCardBalance(CardInfo card) {
        String text = getCard(card).getText();
        Matcher matcher = BALANCE_PATTERN.matcher(text);
        if (!matcher.find()) {
            throw new IllegalStateException("Не удалось определить баланс карты: " + text);
        }
        return Integer.parseInt(matcher.group(1));
    }

    public TransferPage selectCardToDeposit(CardInfo card) {
        getCard(card).$("[data-test-id=action-deposit]").click();
        return new TransferPage();
    }

    private SelenideElement getCard(CardInfo card) {
        return $("[data-test-id='" + card.testId() + "']").shouldBe(visible);
    }
}
