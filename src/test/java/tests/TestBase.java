package tests;

import com.codeborne.selenide.Configuration;
import drivers.BrowserstackDriver;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    public static void setUp() {
        Configuration.browser = BrowserstackDriver.class.getName(); // Использование кастомного драйвера
    }
}
