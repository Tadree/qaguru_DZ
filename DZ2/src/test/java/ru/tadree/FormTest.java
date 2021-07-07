package ru.tadree;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormTest {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void positiveFillTest () {

        open("/automation-practice-form");

        //заполнение формы
        $("#firstName").setValue("Elena");
        $("#lastName").setValue("Nikitina");
        $("#userEmail").setValue("test@test.ru");
        $("#genterWrapper").$(byText("Other")).click();
        $("#userNumber").setValue("0123456789");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__day--001").click();
        $("#subjectsInput").setValue("Physics").pressEnter();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/DZ2.png"));
        $("#currentAddress").setValue("test");
        $("#react-select-3-input").setValue("Uttar Pradesh").pressEnter();
        $("#react-select-4-input").setValue("Agra").pressEnter();

        $("#submit").scrollTo().click();

        //проверка введенных данных
        $(".table-responsive").shouldHave(text("Elena Nikitina"), text("test@test.ru"),
                text("Other"), text("0123456789"), text("01 January,2000"), text("Physics"),
                text("Music"), text("DZ2.png"), text("test"), text("Uttar Pradesh Agra"));

    }
}
