package com.publbox.publBox;

import com.publbox.core.WebDriverTestBase;
import com.publbox.pages.publeBox.PublBoxMainPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Listeners({com.publbox.core.TestListener.class})
public class PublBoxMainPageTest extends WebDriverTestBase {

    @Title("Main page testing")
    @Description("Setting everything up")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void mainPage() {
        PublBoxMainPage mainPage = new PublBoxMainPage();
        mainPage.openPage("https://publbox.com/")
                .isPageopened("Facebook");
    }
}
