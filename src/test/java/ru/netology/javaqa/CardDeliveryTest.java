package ru.netology.javaqa;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    LocalDate today = LocalDate.now();
    LocalDate date = today.plusDays(3);
    String format = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @Test
    void shouldFillOutCardForm() {

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(format);
        $("[data-test-id='name'] input").setValue("Ксения Рифатовна Линн");
        $("[data-test-id='phone'] input").setValue("+79114110437");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='notification']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно забронирована"));


    }
}
