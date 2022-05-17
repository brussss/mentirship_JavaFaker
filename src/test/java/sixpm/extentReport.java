package sixpm;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
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

public class extentReport {
        @BeforeAll
        public static void OpenMaxWindow() {
            Configuration.browserSize = "1920x1080";
            SelenideLogger.addListener("allure", new AllureSelenide());
        }
        @BeforeEach
        void OpenUrl() {open("https://www.6pm.com/");}

    @Test
        void main() {

            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReports.html");

            ExtentReports extent = new ExtentReports();
            extent.attachReporter(htmlReporter);

            ExtentTest test1 = extent.createTest("6PM Simple Choose");
            var productName = "Gf6026";
            var productId = "5727125";
            var password = "Banderstadt1@";
            Faker fake = new Faker();

            test1.log(Status.INFO, "Starting Test");

            OpenMaxWindow();
            test1.pass("Open 6PM with screen size 1920x1080");

            $(By.id("searchAll")).setValue(productName).pressEnter();
            $("#main").shouldHave(text(productName));
            test1.pass("Product search");

            $(by("data-style-id", String.valueOf(productId))).click();
            test1.pass("Click on the product with redirect to the detail info");

            $("#productRecap").shouldBe(Condition.visible, text("59 mm GF6153"));
            test1.pass("Should be present correct info");

            $(By.cssSelector("[data-stock-id='53863828']")).click();
            test1.pass("Add to cart");

            $(By.xpath("/html/body/div[6]/div/div[3]/form/button")).click();
            test1.pass("Go to registration page");

            $(By.id("createAccountSubmit")).click();
            test1.pass("Go to Create new account");

            $(By.id("ap_customer_name")).setValue(fake.name().firstName());
            $(By.id("ap_email")).setValue(fake.internet().safeEmailAddress());
            $(By.id("ap_password")).setValue(password);
            $(By.id("ap_password_check")).setValue((password));
            $(By.className("a-button-input")).click();
            sleep(3000);
            test1.pass("Account created");

            $(By.id("fullName")).setValue(fake.name().fullName());
            $(By.id("addressLine1")).setValue(fake.address().fullAddress());
            $(By.id("addressLine2")).setValue(fake.address().fullAddress());
            $(By.id("city")).setValue(fake.address().city());
            $(By.id("stateOrRegion")).setValue(fake.address().state());
            $(By.id("postalCode")).setValue(fake.address().zipCode());
            $(By.id("primaryVoiceNumber")).setValue(fake.phoneNumber().phoneNumber());
            $("[action='tbd']").click();
            test1.pass("Filling in delivery information");
            sleep(3000);
            test1.pass("Test Completed, thank you for your attention");

            extent.flush();
        }
}
