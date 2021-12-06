package ru.netology.patterns.pages.notifications;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SuccessNotification extends Notification {
    @Override
    protected SelenideElement getRoot() {
        return $("div[data-test-id='success-notification']");
    }
}
