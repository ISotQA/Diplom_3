package site.nomoreparties.stellarburgers.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import site.nomoreparties.stellarburgers.dto.User;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class UserClient extends RestClient {

    private static final String REGISTER = "auth/register";
    private static final String USER_LOGIN = "auth/login";
    private static final String USER_CHANGE = "auth/user";
    private static String accessToken;

    public static String getAccessToken() {
        return accessToken;
    }

    private static String refreshToken;

    @Step("Получение токенов")
    public void getTokens(ValidatableResponse response) {
        if (response.extract().statusCode() == SC_OK) {
            accessToken = response.extract().jsonPath().getString("accessToken").replace("Bearer ", "");
            refreshToken = response.extract().jsonPath().getString("refreshToken").replace("Bearer ", "");
        }
    }

    @Step("Удаление пользователя")
    public ValidatableResponse delete() {
        return given()
                .spec(getDefaultRequestSpec())
                .auth().oauth2(accessToken)
                .when()
                .delete(USER_CHANGE)
                .then();
    }

    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        ValidatableResponse response = given()
                .spec(getDefaultRequestSpec())
                .body(user)
                .when()
                .post(REGISTER)
                .then();
        getTokens(response);
        return response;
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse loginUser(User user) {
        ValidatableResponse response = given()
                .spec(getDefaultRequestSpec())
                .body(user)
                .post(USER_LOGIN)
                .then();
        getTokens(response);
        return response;
    }
}

