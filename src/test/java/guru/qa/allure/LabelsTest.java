package guru.qa.allure;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LabelsTest {
    @Test
    @Owner("TMolonushenko")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://github.com")
    @Feature("Задачи в репозитории")
    @Story("Создание новой задачи в репозитории")
    @DisplayName("Проверка создания Issue для авторизованного пользователя")
    @Description("Этот тест проверяет создание Issue...")
    public void testAnnotatedLabels(){

    }
    @Test
    public void testDynamicLabels(){
        Allure.label("owner", "TMolonushenko");
        Allure.label("Severity", SeverityLevel.CRITICAL.value());
        Allure.feature("Задачи в репозитории");
        Allure.story("Удаление новой задачи в репозитории");
        Allure.getLifecycle().updateFixture(testCase -> {
            testCase.setName("Проверка удаление Issue для авторизированнного пользователя");
        });
        Allure.description("Этот тест проверяет создание Issue....");
        Allure.link("Testing", "https://github.com");
    }

    @Test
    public void testParameters() {
        Allure.parameter("Регион", "Москвовская область");
        Allure.parameter("Город", "Москва");

    }

}
