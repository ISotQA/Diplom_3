package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.dto.User;
import site.nomoreparties.stellarburgers.generator.UserGenerator;
import site.nomoreparties.stellarburgers.pageobject.RegistrationPage;
import site.nomoreparties.stellarburgers.user.UserClient;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.junit.Assert.assertEquals;

public class RegisterTest extends BaseUITest {

    private User user;
    private UserClient userClient;
    private UserGenerator userGenerator;
    private RegistrationPage registrationPage;

    private String email;
    private String password;
    private String name;

    @Before
    public void setUp() {
        open(REGISTRATION_PAGE);
        userClient = new UserClient();
        user = new User();
    }

    @After
    public void tearDown() {
        user.setEmail(email);
        user.setPassword(password);
        String token = userClient.loginUser(user)
                .extract()
                .path("accessToken");
        if (token != null) {
            userClient.delete()
                    .assertThat()
                    .statusCode(SC_ACCEPTED);
        }
    }

    @Test
    @DisplayName("Регистрация")
    @Description("Проверка регистрации пользователя с переходом на страницу авторизации")
    public void registrationUserTest() {
        registrationPage = new RegistrationPage();
        userGenerator = new UserGenerator();
        name = userGenerator.getValue(5);
        email = userGenerator.getRandomEmail(10);
        password = userGenerator.getValue(7);
        registrationPage.registrationUser(name, email, password);
        assertEquals("Вход", registrationPage.getHeaderInputLoginPageText());

    }

    @Test
    @DisplayName("Регистрация")
    @Description("Проверка получения ошибки при вводе пароля менее 6 символов")
    public void userRegistrationErrorTest() {
        registrationPage = new RegistrationPage();
        userGenerator = new UserGenerator();
        name = userGenerator.getValue(5);
        email = userGenerator.getRandomEmail(10);
        password = userGenerator.getValue(4);
        registrationPage.registrationUser(name, email, password);
        assertEquals("Некорректный пароль", registrationPage.getErrorMessage());
    }
}
