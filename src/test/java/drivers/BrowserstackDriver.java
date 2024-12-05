package drivers;

import com.codeborne.selenide.WebDriverProvider;
import com.codeborne.selenide.WebDriverRunner;
import config.TestConfig;
import io.appium.java_client.remote.MobileCapabilityType;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;


    public class BrowserstackDriver implements WebDriverProvider {

        private static final TestConfig config = ConfigFactory.create(TestConfig.class);

        @Nonnull
        @Override
        public WebDriver createDriver(@Nonnull Capabilities capabilities) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.merge(capabilities);

            // Установка параметров в зависимости от платформы
            String platform = config.platform();
            if ("ios".equalsIgnoreCase(platform)) {
                caps.setCapability("platformName", "iOS");
                caps.setCapability("deviceName", "iPhone 13 Pro");
                caps.setCapability("automationName", "XCUITest");
            } else {
                caps.setCapability("platformName", "Android");
                caps.setCapability("deviceName", "Android Emulator");
                caps.setCapability("automationName", "UiAutomator2");
            }

            // Параметры для Browserstack
            caps.setCapability("app", config.appUrl());
            caps.setCapability("browserstack.user", config.browserstackUser());
            caps.setCapability("browserstack.key", config.browserstackKey());

            try {
                return new RemoteWebDriver(new URL(config.browserstackUrl()), caps);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid BrowserStack URL", e);
            }
        }
}
