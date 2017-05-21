package com.publbox.pages.publeBox;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PublBoxMainPage {
    private SelenideElement pageIdentificator = $(By.cssSelector(".btn.btn-facebook.byyd.btn-block"));

    @Step("Open page")
    public PublBoxMainPage openPage(String url) {
        open(url);
        return this;
    }

    @Step("Check: page {0} opened")
    public PublBoxMainPage isPageopened(String title) {
        System.out.println(pageIdentificator.getText());
        pageIdentificator.shouldHave(Condition.text(title));
        return this;
        //or change the returning tyoe of the method into NewPage
        //return page(NewPage.class)
    }
}
