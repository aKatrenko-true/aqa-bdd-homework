package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper.AuthInfo;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement login = $("[data-test-id=login] input");
    private final SelenideElement password = $("[data-test-id=password] input");
    private final SelenideElement continueButton = $("[data-test-id=action-login]");

    public VerificationPage login(AuthInfo authInfo) {
        login.setValue(authInfo.login());
        password.setValue(authInfo.password());
        continueButton.click();
        return new VerificationPage();
    }
}
