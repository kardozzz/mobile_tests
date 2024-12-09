package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.TestConfigAndroid;
import config.TestConfigIOS;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;


public class BrowserstackDriver implements WebDriverProvider {

    private static final TestConfigAndroid androidConfig = ConfigFactory.create(TestConfigAndroid.class);
    private static final TestConfigIOS iosConfig = ConfigFactory.create(TestConfigIOS.class);

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.merge(capabilities);

        String platform = System.getProperty("platform", "android");

        if ("ios".equalsIgnoreCase(platform)) {
            caps.setCapability("platformName", iosConfig.platform());
            caps.setCapability("deviceName", iosConfig.device());
            caps.setCapability("os_version", iosConfig.osVersion());
            caps.setCapability("app", iosConfig.appUrl());
            caps.setCapability("browserstack.user", iosConfig.browserstackUser());
            caps.setCapability("browserstack.key", iosConfig.browserstackKey());
        } else {
            caps.setCapability("platformName", androidConfig.platform());
            caps.setCapability("deviceName", androidConfig.device());
            caps.setCapability("os_version", androidConfig.osVersion());
            caps.setCapability("app", androidConfig.appUrl());
            caps.setCapability("browserstack.user", androidConfig.browserstackUser());
            caps.setCapability("browserstack.key", androidConfig.browserstackKey());
        }

        try {
            return new RemoteWebDriver(new URL(androidConfig.browserstackUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid BrowserStack URL", e);
        }
    }
}
