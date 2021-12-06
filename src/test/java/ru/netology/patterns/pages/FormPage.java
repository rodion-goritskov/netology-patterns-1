package ru.netology.patterns.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.patterns.data.FormData;
import ru.netology.patterns.pages.notifications.ReplanNotification;
import ru.netology.patterns.pages.notifications.SuccessNotification;
import ru.netology.patterns.utils.KeyboardUtils;

import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FormPage {
    private final SelenideElement appointmentDateField = $("input[placeholder='Дата встречи']");
    private final SelenideElement citySelector = $("input[placeholder=Город]");
    private final SelenideElement nameInput = $("input[name=name]");
    private final SelenideElement phoneInput = $("input[name=phone]");

    public FormPage fillForm(FormData form) {
        if (form.getDate() != null) {
            appointmentDateField.click();
            appointmentDateField.sendKeys(KeyboardUtils.selectTextChord(), Keys.BACK_SPACE);
            appointmentDateField.sendKeys(DateTimeFormatter.ofPattern("dd.MM.yyyy").format(form.getDate()));
        }

        if (form.getCity() != null) {
            citySelector.clear();
            citySelector.sendKeys(form.getCity());
            $$("span[class=menu-item__control]").find(text(form.getCity())).click();
        }

        if (form.getFullName() != null) {
            nameInput.clear();
            nameInput.sendKeys(form.getFullName());
        }

        if (form.getMobilePhone() != null) {
            phoneInput.clear();
            phoneInput.sendKeys(form.getMobilePhone());
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
