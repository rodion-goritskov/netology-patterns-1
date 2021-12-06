package ru.netology.patterns.pages.notifications;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;

public abstract class Notification {
    protected abstract SelenideElement getRoot();

    public String getTitle() {
        return getRoot().$(".notification__title").shouldBe(visible).text();
    }

    public String getText() {
        return getRoot().$(".notification__content").shouldBe(visible).text();
    }
}
