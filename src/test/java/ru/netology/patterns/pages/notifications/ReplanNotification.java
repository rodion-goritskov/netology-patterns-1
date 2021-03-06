package ru.netology.patterns.pages.notifications;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ReplanNotification extends Notification {
    @Override
    protected SelenideElement getRoot() {
        return $("div[data-test-id='replan-notification']").shouldBe(visible);
    }

    public void replan() {
        getRoot().$x(".//button[contains(., 'Перепланировать')]").click();
    }
}
