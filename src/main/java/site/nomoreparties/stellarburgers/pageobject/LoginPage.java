package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage{
    private SelenideElement loginButton = $(byXpath(".//button[text()='Войти']"));

    @Step("Выполнение авторизации")
    public void authorizationUser(String email, String password) {
        setEmailField(email);
        setPasswordField(password);
        clickRegistrationButton();
    }

    @Step("Выполнение клика по кнопке 'Зарегистрироваться'")
    public void clickRegistrationButton() {
        loginButton.click();
    }
}
