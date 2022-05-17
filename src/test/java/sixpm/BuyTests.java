package sixpm;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;

public class BuyTests {
    @BeforeAll
    private static void OpenMaxWindow() {
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @BeforeEach
    void OpenUrl() {open("https://www.6pm.com/");}

    @Test
    void SearchProductByIdAndBuy() {
        var productName = "GF6026";
        var productId = "5727125";
        var password = "Banderstadt1@";
        Faker fake = new Faker();

        OpenMaxWindow();

        $(By.id("searchAll")).setValue(productName).pressEnter();
        $("#main").shouldHave(text(productName));

        $(by("data-style-id", String.valueOf(productId))).click();

        $("#productRecap").shouldBe(Condition.visible, text("59 mm GF6153"));

        $(By.cssSelector("[data-stock-id='53863828']")).click();

        $(By.xpath("/html/body/div[6]/div/div[3]/form/button")).click();

        $(By.id("createAccountSubmit")).click();

        $(By.id("ap_customer_name")).setValue(fake.name().firstName());
        $(By.id("ap_email")).setValue(fake.internet().safeEmailAddress());
        $(By.id("ap_password")).setValue(password);
        $(By.id("ap_password_check")).setValue((password));
        $(By.className("a-button-input")).click();
        sleep(3000);

        $(By.id("fullName")).setValue(fake.name().fullName());
        $(By.id("addressLine1")).setValue(fake.address().fullAddress());
        $(By.id("addressLine2")).setValue(fake.address().fullAddress());
        $(By.id("city")).setValue(fake.address().city());
        $(By.id("stateOrRegion")).setValue(fake.address().state());
        $(By.id("postalCode")).setValue(fake.address().zipCode());
        $(By.id("primaryVoiceNumber")).setValue(fake.phoneNumber().phoneNumber());
        $("[action='tbd']").click();
        sleep(3000);
    }
}
