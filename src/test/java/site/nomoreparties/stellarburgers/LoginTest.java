package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.dto.User;
import site.nomoreparties.stellarburgers.generator.UserGenerator;
import site.nomoreparties.stellarburgers.pageobject.LoginPage;
import site.nomoreparties.stellarburgers.pageobject.MainPage;
import site.nomoreparties.stellarburgers.pageobject.RegistrationPage;
import site.nomoreparties.stellarburgers.user.UserClient;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;

public class LoginTest extends BaseUITest {
    private UserGenerator userGenerator;
    private User user;
    private UserClient userClient;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private MainPage mainPage;
    private String name;
    private String email;
    private String password;
    private String token;

    @Before
    public void setUp() {
        registrationPage = new RegistrationPage();
        loginPage = new LoginPage();
        userGenerator = new UserGenerator();
        mainPage = new MainPage();
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
    }

    @After
    public void tearDown() {
        Selenide.clearBrowserLocalStorage();
        if (token != null) {
            userClient.delete()
                    .assertThat()
                    .statusCode(SC_ACCEPTED);
        }
    }

    @Test
    @DisplayName("Авторизация")
    @Description("Проверка авторизации по кнопке 'Войти в аккаунт' на главной странице")
    public void userShouldBeAuthorizedLoginButtonTest() {
        open(MAIN_PAGE);
        mainPage.loginButtonClick();
        assertEquals("Вход", registrationPage.getHeaderInputLoginPageText());
        loginPage.authorizationUser(email, password);
        assertEquals("Оформить заказ", mainPage.getPlaceOrder());
    }

    @Test
    @DisplayName("Авторизация")
    @Description("Проверка авторизации по кнопке 'Личный кабинет")
    public void userShouldBeAuthorizedPersonalAreaButtonTest() {
        open(MAIN_PAGE);
        mainPage.personalAreaButtonClick();
        assertEquals("Вход", registrationPage.getHeaderInputLoginPageText());
        loginPage.authorizationUser(email, password);
        assertEquals("Оформить заказ", mainPage.getPlaceOrder());
    }

    @Test
    @DisplayName("Авторизация")
    @Description("Проверка авторизации со страницы регистрации")
    public void userShouldBeAuthorizedRegistrationFormTest() {
        open(REGISTRATION_PAGE);
        registrationPage.inputButtonClick();
        assertEquals("Вход", registrationPage.getHeaderInputLoginPageText());
        loginPage.authorizationUser(email, password);
        assertEquals("Оформить заказ", mainPage.getPlaceOrder());
    }

    @Test
    @DisplayName("Авторизация")
    @Description("Проверка авторизации со страницы восстановления пароля")
    public void userShouldBeAuthorizedPasswordRecoveryFormTest() {
        open(RECOVERY_PASSWORD_PAGE);
        registrationPage.inputButtonClick();
        assertEquals("Вход", registrationPage.getHeaderInputLoginPageText());
        loginPage.authorizationUser(email, password);
        assertEquals("Оформить заказ", mainPage.getPlaceOrder());
    }
}
