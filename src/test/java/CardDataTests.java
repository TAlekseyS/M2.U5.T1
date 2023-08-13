import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import data.DataGeneartor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
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
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(visible);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(secondMeetingDate);
        $(byText("Запланировать")).click();
        $("[data-test-id='replan-notification'] .notification__content")
         .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"))
         .shouldBe(visible);
        $(withText("Перепланировать")).click();
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + secondMeetingDate))
                .shouldBe(visible);
        $(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));

    }


}

