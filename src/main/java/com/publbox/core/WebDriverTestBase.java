package com.publbox.core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.WebDriverRunner.CHROME;
import static com.codeborne.selenide.WebDriverRunner.FIREFOX;


public abstract class WebDriverTestBase {
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final String BROWSER = System.getProperty("browser");
    private static final Logger LOG = LogManager.getLogger(WebDriverTestBase.class);

    private DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    @BeforeSuite
    public void setUp() {
        if (isWindows()) {
            if (isBrowserSetUpFor(CHROME, BROWSER)) {
                ChromeDriverManager.getInstance().setup();
                LOG.info("Win + Chrome is chosen");
            } else if (isBrowserSetUpFor(FIREFOX, BROWSER)) {
                FirefoxDriverManager.getInstance().setup();
                LOG.info("Win + Firefox is chosen");
            }
        } else if (isUnix()) {
            if (isBrowserSetUpFor(FIREFOX, BROWSER)) {
                ChromeDriverManager.getInstance().setup();
                LOG.info("Unix + Chrome is chosen");
            } else if (isBrowserSetUpFor(FIREFOX, BROWSER)) {
                FirefoxDriverManager.getInstance().setup();
                LOG.info("Unix + Firefox is chosen");
            }
        }
        initializeWebDriver();
    }

    private void initializeWebDriver() {
        try {
            if (isBrowserSetUpFor(CHROME, BROWSER)) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                Configuration.browser = CHROME;
                desiredCapabilities.setBrowserName(CHROME);
                LOG.info("Chrome driver is initialized");
            } else if (isBrowserSetUpFor(FIREFOX, BROWSER)) {
                Configuration.browser = WebDriverRunner.FIREFOX;
                desiredCapabilities.setBrowserName(FIREFOX);
                LOG.info("Firefox driver is initialized");
            }
            Configuration.startMaximized = true;
        } catch (WebDriverException e) {
            LOG.error(e.getMessage());
            WindowsUtils.killByName(desiredCapabilities.getBrowserName() + "driver" + (isWindows() ? ".exe" : ""));
        }
    }

    //есть смысл использовать с селенидом, только если создан кастомный драйвер с капабилитями и setWebDriver(customDriver)
    /*@AfterClass
    public void tearDown() {
        if (driver != null) {
            close();
        }
    }*/

    private boolean isBrowserSetUpFor(String browserName, String browserSystemVeriable) {
        return StringUtils.isEmpty(BROWSER) || browserName.equalsIgnoreCase(browserSystemVeriable);
    }

    private boolean isWindows() {
        return OS.contains("win");
    }

    private boolean isUnix() {
        return OS.contains("nix") || OS.contains("nux") || OS.contains("aix");
    }
}
