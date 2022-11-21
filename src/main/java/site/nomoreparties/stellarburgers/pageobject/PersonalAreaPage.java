package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class PersonalAreaPage {
    private SelenideElement profileButton = $(byText("Профиль"));
    private SelenideElement constructorButton = $(byText("Конструктор"));
    private SelenideElement stellarBarLogotype = $(byXpath("//div/a/*[name()='svg']"));
    private SelenideElement logoutButton = $(byText("Выход"));


    @Step("Выполнение клика по кнопке 'Конструктор'")
    public void constructorButtonClick(){
        constructorButton.click();
    }

    @Step("Выполнение клика по логотипу 'Stellar Burgers'")
    public void stellarBarLogotypeClick(){
        stellarBarLogotype.click();
    }

    @Step("Выполнение клика по кнопке 'Выход'")
    public void logoutButtonClick(){
        logoutButton.click();
    }

    @Step("Получение имени кнопки 'Профиль'")
    public String getProfileButtonName(){
        return profileButton.getText();
    }
}
