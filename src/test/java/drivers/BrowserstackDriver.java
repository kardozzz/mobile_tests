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
import java.util.HashMap;


public class BrowserstackDriver implements WebDriverProvider {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.merge(capabilities);

        String platform = System.getProperty("platform", "android");

        if ("ios".equalsIgnoreCase(platform)) {
            TestConfigIOS iosConfig = ConfigFactory.create(TestConfigIOS.class);
            caps.setCapability("appium:deviceName", iosConfig.device());
            caps.setCapability("appium:osVersion", iosConfig.osVersion());
            caps.setCapability("appium:app", iosConfig.appUrl());
            caps.setCapability("bstack:options", new HashMap<String, Object>() {{
                put("userName", iosConfig.browserstackUser());
                put("accessKey", iosConfig.browserstackKey());
            }});
        } else {
            TestConfigAndroid androidConfig = ConfigFactory.create(TestConfigAndroid.class);
            caps.setCapability("appium:deviceName", androidConfig.device());
            caps.setCapability("appium:osVersion", androidConfig.osVersion());
            caps.setCapability("appium:app", androidConfig.appUrl());
            caps.setCapability("bstack:options", new HashMap<String, Object>() {{
                put("userName", androidConfig.browserstackUser());
                put("accessKey", androidConfig.browserstackKey());
            }});
        }

        try {
            return new RemoteWebDriver(new URL(System.getProperty("browserstack.url")), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid BrowserStack URL", e);
        }
    }
}
