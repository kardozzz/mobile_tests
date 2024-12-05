package tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {

    @Test
    void successfulSearchTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    void findSpringBootTest() {
        step("Нажимаем на поле поиска", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            // Вводим запрос "Spring Boot"
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Spring Boot");
        });

        step("Подождать загрузки списка элементов", () -> {
            // Убедимся, что список элементов появился
            $$(id("org.wikipedia.alpha:id/page_list_item_description"))
                    .shouldBe(CollectionCondition.sizeGreaterThan(0));
        });

        step("Находим элемент с точным текстом и кликаем по нему", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_description"))
                    .findBy(text("Java framework to create enterprise grade applications"))
                    .click();
        });

        step("Проверяем, что открылась страница с ошибкой", () -> {

            $(id("org.wikipedia.alpha:id/view_wiki_error_text"))
                    .shouldBe(Condition.visible);
        });
    }
}
