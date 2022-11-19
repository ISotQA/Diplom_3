package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class PersonalAreaPage {
    private SelenideElement profileButton = $(byText("Профиль"));
    private SelenideElement constructorButton = $(byText("Конструктор"));
    private SelenideElement stellarBarLogotype = $(byXpath("//div/a/*[name()='svg']"));
    private SelenideElement logoutButton = $(byText("Выход"));


    public void constructorButtonClick(){
        constructorButton.click();
    }

    public void stellarBarLogotypeClick(){
        stellarBarLogotype.click();
    }

    public void logoutButtonClick(){
        logoutButton.click();
    }

    public String getProfileButtonName(){
        return profileButton.getText();
    }
}
