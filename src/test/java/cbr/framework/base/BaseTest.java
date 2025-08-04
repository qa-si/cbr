package cbr.framework.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    @BeforeEach
    public void prepare() {
        open("https://cbr.ru");
    }

    @AfterEach()
    public void afterMethod() {
        System.gc();
    }
}
