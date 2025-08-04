package cbr.tests;

import cbr.framework.base.BaseTest;
import cbr.project.enums.Currency;
import cbr.project.models.Rates;
import cbr.project.object.pages.KeyIndicatorsPage;
import cbr.project.object.pages.StartPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;

import static cbr.framework.utils.DataHelper.stringToFloat;
import static cbr.framework.utils.JSONHelper.compareWithEtalon;
import static cbr.framework.utils.JSONHelper.jsonStringToFile;
import static com.codeborne.selenide.Selenide.refresh;

public class GetCourseTest extends BaseTest {

    private KeyIndicatorsPage keyIndicatorsPage;
    private StartPage startPage;
    private Rates rates;
    private final HashMap<String, Float> data = new HashMap<>();
    private static final String FILE_NAME = "etalon.json";
    private File jsonFile;

    @BeforeEach
    void preconditions() {
        keyIndicatorsPage = new KeyIndicatorsPage();
        startPage = new StartPage();

        startPage.rightBar.mainIndicatorsButtonClick();
        keyIndicatorsPage.scrollToCurrencyTable();
        data.put("yuanRate", stringToFloat(keyIndicatorsPage.getRate(Currency.YUAN)) - (float) Math.random());
        data.put("dollarRate", stringToFloat(keyIndicatorsPage.getRate(Currency.DOLLAR)) - (float) Math.random());
        data.put("euroRate", stringToFloat(keyIndicatorsPage.getRate(Currency.EURO)) - (float) Math.random());

        jsonStringToFile(data, FILE_NAME);
        jsonFile = new File(FILE_NAME);

        refresh();
    }

    @DisplayName("Сравнение курсов валют после изменения")
    @Test
    void test() {
        keyIndicatorsPage.scrollToCurrencyTable();
        rates = new Rates(
                stringToFloat(keyIndicatorsPage.getRate(Currency.YUAN)),
                stringToFloat(keyIndicatorsPage.getRate(Currency.DOLLAR)),
                stringToFloat(keyIndicatorsPage.getRate(Currency.EURO)));

        compareWithEtalon(jsonFile, rates);
    }

    @AfterEach
    void clean() {
        if (jsonFile.exists()) {
            jsonFile.deleteOnExit();
        }
    }
}
