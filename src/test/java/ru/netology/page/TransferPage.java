package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper.CardInfo;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement amount = $("[data-test-id=amount] input");
    private final SelenideElement from = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public TransferPage() {
        $("[data-test-id=amount]").shouldBe(visible);
    }

    public DashboardPage transfer(int transferAmount, CardInfo sourceCard) {
        amount.setValue(String.valueOf(transferAmount));
        from.setValue(sourceCard.number());
        transferButton.click();
        return new DashboardPage();
    }
}
