package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage extends BasePage {

    private SelenideElement registrationButton = $(byXpath(".//button[text()='Зарегистрироваться']"));
    private SelenideElement registrationError = $(byText("Некорректный пароль"));
    private SelenideElement headerInputLoginPage = $(byText("Вход"));
    private SelenideElement inputButton = $(byText("Войти"));

    @Step("Регистрация пользователя")
    public void registrationUser(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
        registrationButtonClick();
    }

    @Step("Выполнение клика по кнопке 'Зарегистрироваться'")
    public void registrationButtonClick() {
        registrationButton.click();
    }

    @Step("Получение сообщения об ошибке при регистрации")
    public String getErrorMessage() {
        return registrationError.getText();
    }

    @Step("Получение текста заголовка")
    public String getHeaderInputLoginPageText() {
        return headerInputLoginPage.getText();
    }

    @Step("Выполнение клика по кнопке 'Войти'")
    public void inputButtonClick() {
        inputButton.click();
    }
}
