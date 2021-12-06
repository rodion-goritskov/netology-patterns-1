package ru.netology.patterns.utils;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.Keys;

public class KeyboardUtils {
    public static String selectTextChord() {
        if (Selenide.getUserAgent().contains("Mac OS X")) {
            return Keys.chord(Keys.COMMAND, "a");
        } else {
            return Keys.chord(Keys.CONTROL, "a");
        }
    }
}
