package cbr.project.enums;

import lombok.Getter;

@Getter
public enum Currency {

    YUAN("Юань"),
    DOLLAR("Доллар США"),
    EURO("Евро");

    private final String value;

    Currency(String value) {
        this.value = value;
    }

}