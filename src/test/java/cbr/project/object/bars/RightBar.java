package cbr.project.object.bars;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

/**
 * Класс описывает правую боковую панель
 */
public class RightBar {

    private final String MAIN_INDICATORS_BUTTON = ".main-indicator_rates-link";

    @Step("Нажать на кнопку 'Все показатели'")
    public void mainIndicatorsButtonClick() {
        $(MAIN_INDICATORS_BUTTON).scrollIntoView(true).click();
    }
}
