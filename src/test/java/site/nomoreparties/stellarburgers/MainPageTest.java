package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.pageobject.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;
import static site.nomoreparties.stellarburgers.BaseUITest.MAIN_PAGE;

public class MainPageTest {

    private MainPage mainPage;
    @Before
    public void setUp(){
        mainPage = new MainPage();
        open(MAIN_PAGE);
    }

    @After
    public void tearDown(){
        Selenide.refresh();
    }

    @Test
    @DisplayName("Раздел «Конструктор»")
    @Description("Проверка перехода к разделу 'Булки'")
    public void jumpToChapterBunsTest(){
        mainPage.selectChapter("Соусы");
        mainPage.selectChapter("Булки");
        assertTrue(mainPage.checkChapter("Булки"));
    }

    @Test
    @DisplayName("Раздел «Конструктор»")
    @Description("Проверка перехода к разделу 'Соусы'")
    public void jumpToChapterSaucesTest(){
        mainPage.selectChapter("Соусы");
        assertTrue(mainPage.checkChapter("Соусы"));
    }

    @Test
    @DisplayName("Раздел «Конструктор»")
    @Description("Проверка перехода к разделу 'Начинки'")
    public void jumpToChapterFillingsTest(){
        mainPage.selectChapter("Начинки");
        assertTrue(mainPage.checkChapter("Начинки"));
    }
}
