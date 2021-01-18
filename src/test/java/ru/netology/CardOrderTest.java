package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    @Test
    void orderAccepted(){
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Семен Семенович");
        $("[data-test-id=phone] input").setValue("+88005553535");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(By.className("paragraph")).shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void orderNotAcceptedCauseName(){
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Simon Simonovich");
        $("[data-test-id=phone] input").setValue("+88005553535");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void orderNotAcceptedCausePhone(){
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Семен Семенович");
        $("[data-test-id=phone] input").setValue("+88005");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void orderNotAcceptedCauseAgreement(){
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Семен Семенович");
        $("[data-test-id=phone] input").setValue("+88005553535");
        $(By.className("button")).click();
        $(".checkbox__text").shouldHave(Condition.cssValue("color", "rgba(255, 92, 92, 1)"));
    }

    @Test
    void orderNotAcceptedCauseNameIsEmpty(){
        open("http://localhost:9999");
        $("[data-test-id=name] input");
        $("[data-test-id=phone] input").setValue("+88005553535");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_type_text .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void orderNotAcceptedCausePhoneIsEmpty(){
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Семен Семенович");
        $("[data-test-id=phone] input");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(".input_type_tel .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }
}
