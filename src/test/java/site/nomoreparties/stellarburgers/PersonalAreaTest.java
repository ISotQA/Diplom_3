package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.dto.User;
import site.nomoreparties.stellarburgers.generator.UserGenerator;
import site.nomoreparties.stellarburgers.pageobject.*;
import site.nomoreparties.stellarburgers.user.UserClient;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;

public class PersonalAreaTest extends BaseUITest {

    private MainPage mainPage;
    private PersonalAreaPage personalAreaPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private UserGenerator userGenerator;
    private User user;
    private UserClient userClient;
    private String name;
    private String email;
    private String password;
    private String token;

    @Before
    public void setUp() {
        open(MAIN_PAGE);
        mainPage = new MainPage();
        personalAreaPage = new PersonalAreaPage();
        loginPage = new LoginPage();
        userGenerator = new UserGenerator();
        mainPage = new MainPage();
        registrationPage = new RegistrationPage();
        name = userGenerator.getValue(7);
        email = userGenerator.getRandomEmail(10);
        password = userGenerator.getValue(6);
        user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userClient = new UserClient();
        token = userClient.createUser(user)
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .extract()
                .path("accessToken");
        mainPage.loginButtonClick();
        loginPage.authorizationUser(email, password);
    }

    @After
    public void tearDown() {
        if (token != null) {
            userClient.delete()
                    .assertThat()
                    .statusCode(SC_ACCEPTED);
        }
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Проверка перехода в личный кабинет по кнопке 'Личный кабинет' с главной страницы")
    public void transitionPersonalAreaInPersonalAreaButtonTest() {
        mainPage.personalAreaButtonClick();
        assertEquals("Профиль", personalAreaPage.getProfileButtonName());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Проверка перехода по клику на 'Конструктор'")
    public void transitionFromPersonalAreaToDesignerByConstructorButtonTest() {
        mainPage.personalAreaButtonClick();
        personalAreaPage.constructorButtonClick();
        assertEquals("Оформить заказ", mainPage.getPlaceOrder());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("роверка перехода по клику на логотип 'Stellar Burgers'")
    public void transitionFromPersonalAreaToDesignerByStellarBarLogotypeTest() {
        mainPage.personalAreaButtonClick();
        personalAreaPage.stellarBarLogotypeClick();
        assertEquals("Оформить заказ", mainPage.getPlaceOrder());
    }

    @Test
    @DisplayName("Выход")
    @Description("Проверка выхода по кнопке «Выйти» в личном кабинете")
    public void logoutTest() {
        mainPage.personalAreaButtonClick();
        personalAreaPage.logoutButtonClick();
        assertEquals("Вход", registrationPage.getHeaderInputLoginPageText());
    }
}
