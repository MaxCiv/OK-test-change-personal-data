package ru.ok.qa.utils;

import ru.ok.qa.models.City;
import ru.ok.qa.models.Gender;
import ru.ok.qa.models.User;

import java.util.Calendar;
import java.util.GregorianCalendar;

public final class UserCreator {

    private UserCreator() {
    }

    public static User getDefaultPreviousUser() {
        User user = new User();
        user.setName("Розалинда");
        user.setSurname("Лютес");
        user.setBirthDate(new GregorianCalendar(1975, Calendar.DECEMBER, 2));
        user.setGender(Gender.MALE);
        user.setResidenceCity(new City("Вашингтон", "Вашингтон, США"));
        user.setBirthCity(new City("Колумбия", "Колумбия, США"));
        return user;
    }

    public static User getDefaultNewUser() {
        User user = new User();
        user.setName("Розалинда");
        user.setSurname("Лютес");
        user.setBirthDate(new GregorianCalendar(1976, Calendar.FEBRUARY, 29));
        user.setGender(Gender.FEMALE);
        user.setResidenceCity(new City("Колумбия", "Колумбия, США"));
        user.setBirthCity(new City("Нью-Йорк", "Нью-Йорк, США"));
        return user;
    }
}
