package ru.netology.patterns;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.patterns.data.DataGenerator;
import ru.netology.patterns.data.FormData;
import ru.netology.patterns.pages.FormPage;
import ru.netology.patterns.pages.notifications.ReplanNotification;
import ru.netology.patterns.pages.notifications.SuccessNotification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MeetingFormTest {
    @Test
    public void rescheduleMeetingIfAlreadyScheduled() {
        FormData initialData = DataGenerator
                .generateDefaultFormData()
                .date(LocalDate.now().plusWeeks(1L))
                .build();

        FormPage formPage = open("/", FormPage.class)
                .fillForm(initialData)
                .toggleAgreement()
                .submit();

        SuccessNotification successNotification = formPage.successNotification();

        assertAll(
                () -> assertThat(successNotification.getText())
                        .contains("Встреча успешно запланирована на")
                        .contains(DateTimeFormatter.ofPattern("dd.MM.yyyy").format(initialData.getDate())),
                () -> assertThat(successNotification.getTitle())
                        .contains("Успешно!")
        );


        FormData updatedMeetingDate = FormData.builder().date(LocalDate.now().plusWeeks(3)).build();
        formPage
                .fillForm(updatedMeetingDate)
                .submit();

        ReplanNotification replanNotification = formPage.replanNotification();

        assertAll(
                () -> assertThat(replanNotification.getText())
                        .contains("У вас уже запланирована встреча на другую дату"),
                () -> assertThat(replanNotification.getTitle())
                        .contains("Необходимо подтверждение")
        );
        replanNotification.replan();

        assertAll(
                () -> assertThat(successNotification.getText())
                        .contains("Встреча успешно запланирована на")
                        .contains(DateTimeFormatter.ofPattern("dd.MM.yyyy").format(updatedMeetingDate.getDate())),
                () -> assertThat(successNotification.getTitle())
                        .contains("Успешно!")
        );
    }
}
