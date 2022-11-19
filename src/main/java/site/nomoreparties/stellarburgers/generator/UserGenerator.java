package site.nomoreparties.stellarburgers.generator;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public String getValue(int count) {
        return RandomStringUtils.randomAlphabetic(count)
                .toLowerCase();
    }

    public String getRandomEmail(int count) {
        return (getValue(count) + "@yandex.ru");
    }
}
