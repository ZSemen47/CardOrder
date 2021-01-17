package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class CardOrderTest {

    @Test
    void shouldTestCardOrder(){
        open("http://localhost:9999");
        SelenideElement form = $("[data-test-id=callback-form]");
        form.$("[data-test-id=name]").setValue("Simon");
        form.$("[data-test-id=phone]").setValue("+88005553535");
        form.$("[data-test-id=agreement]").click();
        SelenideElement result = $("[data-test-id=order-success]");
        result.shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}
