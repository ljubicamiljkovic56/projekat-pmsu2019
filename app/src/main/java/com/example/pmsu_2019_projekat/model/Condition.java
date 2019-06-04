package com.example.pmsu_2019_projekat.model;

public enum Condition {

    TO,
    FROM,
    CC,
    SUBJECT;

    public static int toInt(Condition condition) {
        switch (condition) {
            case TO:
                return 0;
            case FROM:
                return 1;
            case CC:
                return 2;
            default:
                return 3;
        }
    }

}
