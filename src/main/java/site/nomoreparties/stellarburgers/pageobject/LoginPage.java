package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage{
    private SelenideElement loginButton = $(byXpath(".//button[text()='Войти']"));

    public void authorizationUser(String email, String password) {
        setEmailField(email);
        setPasswordField(password);
        clickRegistrationButton();
    }

    public void clickRegistrationButton() {
        loginButton.click();
    }
}
