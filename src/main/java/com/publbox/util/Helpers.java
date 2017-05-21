package com.publbox.util;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import ru.yandex.qatools.allure.annotations.Attachment;
import java.io.File;
import java.io.IOException;

public class Helpers {

    @Attachment(value = "Test {0}", type = "image/png")
    public static byte[] takeScreenShot(String methodName) throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile();
        return Files.toByteArray(screenshot);
    }
}
