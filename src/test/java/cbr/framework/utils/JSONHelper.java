package cbr.framework.utils;

import cbr.project.enums.Currency;
import cbr.project.models.Rates;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static cbr.framework.utils.DataHelper.stringToFloat;
import static java.lang.Float.compare;

/**
 * Класс, содержащий методы-утилиты для работы с JSON
 */
public class JSONHelper {

    @Step("Записывать JSON строку в файл")
    public static void jsonStringToFile(HashMap<String, Float> data, String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(fileName), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step("Сравнить значения из json-файла с объектом класса Rates")
    public static void compareWithEtalon(File etalon, Rates rates) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(etalon);

            compareFloat(rates.getYuanRate(), stringToFloat(jsonNode.get("yuanRate").asText()), Currency.YUAN);
            compareFloat(rates.getDollarRate(), stringToFloat(jsonNode.get("dollarRate").asText()), Currency.DOLLAR);
            compareFloat(rates.getEuroRate(), stringToFloat(jsonNode.get("euroRate").asText()), Currency.EURO);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step("Сравнить значения float {fromRates} и {fromEtalon} для валюты {currency}")
    private static void compareFloat(float fromRates, float fromEtalon, Currency currency) {
        switch (compare(fromRates, fromEtalon)) {
            case 1 -> System.out.println("Курс " + currency + " увеличился на " + (fromRates - fromEtalon));
            case 0 -> System.out.println("Курс " + currency + " не изменился");
            case -1 -> System.out.println("Курс " + currency + " уменьшился на " + (fromEtalon - fromRates));
            default -> throw new IllegalStateException("Unexpected value: " + compare(fromRates, fromEtalon));
        }
    }
}