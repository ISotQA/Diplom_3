package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage extends BasePage {

    private SelenideElement registrationButton = $(byXpath(".//button[text()='Зарегистрироваться']"));
    private SelenideElement registrationError = $(byText("Некорректный пароль"));
    private SelenideElement headerInputLoginPage = $(byText("Вход"));
    private SelenideElement inputButton = $(byText("Войти"));

    public void registrationUser(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
        registrationButtonClick();
    }

    public void registrationButtonClick() {
        registrationButton.click();
    }

    public String getErrorMessage() {
        return registrationError.getText();
    }

    public String getHeaderInputLoginPageText() {
        return headerInputLoginPage.getText();
    }

    public void inputButtonClick() {
        inputButton.click();
    }
}
