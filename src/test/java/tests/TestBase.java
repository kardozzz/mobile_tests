package tests;

import com.codeborne.selenide.Configuration;
import drivers.BrowserstackDriver;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    public static void setUp() {
        String platform = System.getProperty("platform", "android");

        if ("ios".equalsIgnoreCase(platform)) {
            Configuration.browser = BrowserstackDriver.class.getName();
        } else {
            Configuration.browser = BrowserstackDriver.class.getName();
        }

        Configuration.timeout = 15000; // Увеличен таймаут для удаленной работы
    }
}
