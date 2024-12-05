package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

public class IosTests {
    @Test
    void openArticleOnIOS() {
        step("Open search field", () -> {
            $(accessibilityId("Search Wikipedia")).click();
        });

        step("Type search query", () -> {
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Selenium");
        });

        step("Select an article from the list", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                    .findBy(text("Selenium (software)"))
                    .click();
        });

        step("Verify article is opened", () -> {
            $(id("org.wikipedia.alpha:id/view_page_title_text"))
                    .shouldHave(text("Selenium (software)"));
        });
    }
}
