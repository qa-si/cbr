package cbr.framework.utils;

import io.qameta.allure.Step;

/**
 * Класс, содержащий методы-утилиты для работы с разными форматами данных
 */
public class DataHelper {

    @Step("Перевести формат String в формат float")
    public static float stringToFloat(String str) {
        return Float.parseFloat(str.replace(",", "."));
    }
}
