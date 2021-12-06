package ru.netology.patterns.data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {
    private static final Faker FAKER = new Faker(new Locale("ru"));

    private DataGenerator() {}

    public static FormData.FormDataBuilder generateDefaultFormData() {
        return FormData.builder()
                .city(FAKER.address().city())
                .fullName(FAKER.name().fullName())
                .mobilePhone(FAKER.phoneNumber().phoneNumber());
    }
}
