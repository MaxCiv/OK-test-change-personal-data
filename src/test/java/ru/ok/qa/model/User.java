package ru.ok.qa.model;

import java.util.Calendar;
import java.util.Locale;

public class User {

    private String name;
    private String surname;
    private Calendar birthDate;
    private Gender gender;
    private City residenceCity;
    private City birthCity;

    public User() {
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String formPersonalDataDescription() {
        StringBuilder descriptionBuilder = new StringBuilder();
        descriptionBuilder.append(name).append(" ").append(surname);
        if (gender.equals(Gender.FEMALE)) {
            descriptionBuilder.append(", родилась ");
        } else
            descriptionBuilder.append(", родился ");
        descriptionBuilder.append(birthDate.get(Calendar.DAY_OF_MONTH)).append(" ")
                .append(birthDate.getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale("ru"))).append(" ")
                .append(birthDate.get(Calendar.YEAR))
                .append(" в городе ").append(birthCity.getShortName())
                .append(", сейчас живу в городе ").append(residenceCity.getShortName());
        return descriptionBuilder.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public City getResidenceCity() {
        return residenceCity;
    }

    public void setResidenceCity(City residenceCity) {
        this.residenceCity = residenceCity;
    }

    public City getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(City birthCity) {
        this.birthCity = birthCity;
    }
}
