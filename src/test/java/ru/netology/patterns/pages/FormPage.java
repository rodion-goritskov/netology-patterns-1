package ru.netology.patterns.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.patterns.data.FormData;
import ru.netology.patterns.pages.notifications.ReplanNotification;
import ru.netology.patterns.pages.notifications.SuccessNotification;

import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FormPage {
    private void clearAndFill(SelenideElement element, String text) {
        element
                .click();
        element
                .sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        element.sendKeys(text);
    }

    public FormPage fillForm(FormData form) {
        if (form.getDate() != null) {
            clearAndFill($("input[placeholder='Дата встречи']"), DateTimeFormatter.ofPattern("dd.MM.yyyy").format(form.getDate()));
        }

        if (form.getCity() != null) {
            clearAndFill($("input[placeholder=Город]"), form.getCity());
            $$("span[class=menu-item__control]").find(text(form.getCity())).click();
        }

        if (form.getFullName() != null) {
            clearAndFill($("input[name=name]"), form.getFullName());
        }

        if (form.getMobilePhone() != null) {
            clearAndFill($("input[name=phone]"), form.getMobilePhone());
        }

        return this;
    }

    public FormPage toggleAgreement() {
        $("input[name=agreement]").parent().click();
        return this;
    }

    public FormPage submit() {
        $x("//button[contains(., 'Запланировать')]").click();
        return this;
    }

    public SuccessNotification successNotification() {
        return new SuccessNotification();
    }

    public ReplanNotification replanNotification() {
        return new ReplanNotification();
    }
}
