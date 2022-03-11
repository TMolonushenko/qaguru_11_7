package guru.qa.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class StepsTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE_NUMBER = 68;

    @Test
    public void testLambdaSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Ищем репозиторий" + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });

        step("Открываем репозиторий" + REPOSITORY, () -> {
            $(By.linkText("eroshenkoam/allure-example")).click();
        });
        step("Переходим в таб Issue", () -> {
            $(By.partialLinkText("Issues")).click();
            Allure.addAttachment("Page Sourse", "text/html",WebDriverRunner.source(), "html");
        });
        step("Проверяем что существует Issue c номером" + ISSUE_NUMBER, () -> {
            $(withText("#68")).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.openRepository(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeIssueWithNumber(ISSUE_NUMBER);
        steps.takeScreenshot();
    }

}
