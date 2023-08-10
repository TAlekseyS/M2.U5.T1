import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import data.DataGeneartor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class CardDataTests {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }


    @Test
    @DisplayName("Should successful plan meeting")
    void shouldTestCardDelivery() {
        DataGeneartor.UserInfo validUser = DataGeneartor.Registration.generateUser("ru");
        int daysToAddForFirstMeeting = 4;
        String firstMeetingDate = DataGeneartor.generateDate(daysToAddForFirstMeeting);
        int daysToAddForSecondMeeting = 7;
        String secondMeetingDate = DataGeneartor.generateDate(daysToAddForSecondMeeting);


        $("[placeholder=Город]").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[name=name]").setValue(validUser.getName());
        $("[name=phone]").setValue(validUser.getPhone());
        $("[class=checkbox__box]").click();
        $("[class=button__content]").click();
        $(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));
        // $("[placeholder=Город]").setValue("Казань");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(secondMeetingDate);

        $("[class=button__content]").click();
        $("[class=button__content]").click();
        $(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));

        //рабочий хардкодинг

    }


}

