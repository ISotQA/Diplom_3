package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {
    private SelenideElement nameField = $(byXpath(".//label[text()='Имя']/following::input[1]"));
    private SelenideElement emailField = $(byXpath(".//label[text()='Email']/following::input[1]"));
    private SelenideElement passwordField = $(byXpath(".//label[text()='Пароль']/following::input[1]"));

    @Step("Установка значения в поле 'Имя'")
    public void setNameField(String name) {
        nameField.setValue(name);
    }

    @Step("Установка значения в поле 'Email'")
    public void setEmailField(String email) {
        emailField.setValue(email);
    }

    @Step("Установка значения в поле 'Пароль'")
    public void setPasswordField(String password) {
        passwordField.setValue(password);
    }
}
