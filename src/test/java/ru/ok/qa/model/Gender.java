package ru.ok.qa.model;

public enum Gender {

    MALE(1),
    FEMALE(2);

    private int index;

    Gender(int index) {
        this.index = index;
    }
}