package tests;

import com.codeborne.selenide.Configuration;
import drivers.BrowserstackDriver;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    public static void setUp() {
        String platform = System.getProperty("platform", "android"); // Устанавливаем платформу через системное свойство

        if ("ios".equalsIgnoreCase(platform)) {
            Configuration.browser = BrowserstackDriver.class.getName(); // Использование BrowserstackDriver для iOS
        } else {
            Configuration.browser = BrowserstackDriver.class.getName(); // Использование BrowserstackDriver для Android
        }

        Configuration.timeout = 10000; // Установка таймаута ожидания
    }
}
