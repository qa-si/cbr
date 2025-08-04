package cbr.project.object.pages;

import cbr.project.enums.Currency;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс описывает страницу с показателями курсов
 */
public class KeyIndicatorsPage {

    private final String ROW = "tr";
    private final String RATE = "._inner+._end";
    private final String CURRENCY_TABLE_TITLE = "[href='/currency_base/']";

    @Step("Проскроллить страницу до таблицы с курсами валют")
    public void scrollToCurrencyTable() {
        $(CURRENCY_TABLE_TITLE).scrollIntoView(true);
    }

    @Step("Получить курс \"{currency}\"")
    public String getRate(Currency currency) {
        return $$(ROW).findBy(text(currency.getValue())).$(RATE).getText();
    }
}
