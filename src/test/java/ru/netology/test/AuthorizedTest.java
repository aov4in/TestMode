package ru.netology.test;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.cssSelector;

public class AuthorizedTest {

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequestIfUserIsActive() {
        RegUser user = DataGenerator.generateNewActiveUser();
        SelenideElement form = $("[action]");
        form.$(cssSelector("[name=login]")).sendKeys(user.getLogin());
        form.$(cssSelector("[name=password]")).sendKeys(user.getPassword());
        form.$(cssSelector("[type=button]")).click();
        $(byText("Личный кабинет")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotSubmitRequestIfStatusIsBlocked() {
        RegUser user = DataGenerator.generateNewBlockedUser();
        SelenideElement form = $("[action]");
        form.$(cssSelector("[name=login]")).sendKeys(user.getLogin());
        form.$(cssSelector("[name=password]")).sendKeys(user.getPassword());
        form.$(cssSelector("[type=button]")).click();
        $(byText("Ошибка")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void shouldNotSubmitRequestIfLoginInvalid() {
        RegUser user = DataGenerator.generateNewUserWithInvalidLogin();
        SelenideElement form = $("[action]");
        form.$(cssSelector("[name=login]")).sendKeys(user.getLogin());
        form.$(cssSelector("[name=password]")).sendKeys(user.getPassword());
        form.$(cssSelector("[type=button]")).click();
        $(byText("Ошибка")).waitUntil(Condition.visible, 11000);
    }

    @Test
    void shouldNotSubmitRequestIfPasswordInvalid() {
        RegUser user = DataGenerator.generateNewUserWithInvalidPassword();
        SelenideElement form = $("[action]");
        form.$(cssSelector("[name=login]")).sendKeys(user.getLogin());
        form.$(cssSelector("[name=password]")).sendKeys(user.getPassword());
        form.$(cssSelector("[type=button]")).click();
        $(byText("Ошибка")).waitUntil(Condition.visible, 11000);
    }
}
