package ru.ok.qa.models;

public enum Gender {

    MALE(1),
    FEMALE(2);

    private int index;

    Gender(int index) {
        this.index = index;
    }
}
