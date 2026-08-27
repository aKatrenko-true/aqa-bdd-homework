package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper.VerificationCode;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement code = $("[data-test-id=code] input");
    private final SelenideElement continueButton = $("[data-test-id=action-verify]");

    public DashboardPage verify(VerificationCode verificationCode) {
        code.setValue(verificationCode.code());
        continueButton.click();
        return new DashboardPage();
    }
}
