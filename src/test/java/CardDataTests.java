import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;



public class CardDataTests {

    @Test
    void shouldTestCardDelivery()  {
        open("http://localhost:9999");
        // $$(".form")
        // SelenideElement form = $("[action=/]");
        // form.$("[placeholder=Город] input").setValue("Казань");
        $("[placeholder=Город]").setValue("Казань");
        //$("[placeholder=Дата встречи]").setValue("12.08.2023");
        $("[name=name]").setValue("Кирилл Костиев");
        $("[name=phone]").setValue("+77987897897");
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));








        //Thread.sleep(10000);

    }










}
