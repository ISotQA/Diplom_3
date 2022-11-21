package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {

    private SelenideElement loginButton = $(byXpath(".//button[text()='Войти в аккаунт']"));
    private SelenideElement personalAreaButton = $(byXpath(".//p[text()='Личный Кабинет']"));
    private SelenideElement placeOrder = $(byText("Оформить заказ"));
    private String chapterLocator = "//span[text()='%s']";
    private String chapterSelected = "//div[contains(@class, 'tab_tab_type_current__2BEPc')]/span[text()='%s']";

    @Step("Выполнение клика по кнопке 'Войти в аккаунт'")
    public void loginButtonClick() {
        loginButton.click();
    }

    @Step("Получение текста кнопки 'Оформить заказ'")
    public String getPlaceOrder() {
        return placeOrder.getText();
    }

    @Step("Выполнение клика по кнопке 'Личный кабинет'")
    public void personalAreaButtonClick() {
        personalAreaButton.click();
    }

    @Step("Выбор раздела")
    public void selectChapter(String chapterName) {
        SelenideElement chapter = $(byXpath(String.format(chapterLocator, chapterName)));
        chapter.click();
    }

    @Step("Проверка выбран ли раздел")
    public boolean checkChapter(String chapterName) {
        SelenideElement chapter = $(byXpath(String.format(chapterSelected, chapterName)));
       return chapter.isDisplayed();
    }
}
