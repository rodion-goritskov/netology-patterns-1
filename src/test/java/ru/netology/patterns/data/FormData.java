package ru.netology.patterns.data;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class FormData {
    private String city;
    private LocalDate date;
    private String fullName;
    private String mobilePhone;
}
