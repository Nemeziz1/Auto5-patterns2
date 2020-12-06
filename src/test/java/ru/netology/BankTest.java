package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BankTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void requestWithStatusActive() {
        SelenideElement request = $("[action]");
        UserData userData = DataGenerator.statusActive();
        request.$("[class='input__box'] [name='login']").setValue(userData.getLogin());
        request.$("[class='input__box'] [name='password']").setValue(userData.getPassword());
        request.$("[class='button__content']").click();
        $$(".heading").find(exactText("Личный кабинет")).shouldBe(exist);
    }

    @Test
    void requestWithStatusBlocked() {
        SelenideElement request = $("[action]");
        UserData userData = DataGenerator.statusBlocked();
        request.$("[class='input__box'] [name='login']").setValue(userData.getLogin());
        request.$("[class='input__box'] [name='password']").setValue(userData.getPassword());
        request.$("[class='button__content']").click();
        $(byText("Пользователь заблокирован")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void requestWithInvalidLogin() {
        SelenideElement request = $("[action]");
        UserData userData = DataGenerator.invalidLogin();
        request.$("[class='input__box'] [name='login']").setValue(userData.getLogin());
        request.$("[class='input__box'] [name='password']").setValue(userData.getPassword());
        request.$("[class='button__content']").click();
        $(byText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 15000);
    }

    @Test
    void requestWithInvalidPassword() {
        SelenideElement request = $("[action]");
        UserData userData = DataGenerator.invalidPassword();
        request.$("[class='input__box'] [name='login']").setValue(userData.getLogin());
        request.$("[class='input__box'] [name='password']").setValue(userData.getPassword());
        request.$("[class='button__content']").click();
        $(byText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 15000);
    }
}