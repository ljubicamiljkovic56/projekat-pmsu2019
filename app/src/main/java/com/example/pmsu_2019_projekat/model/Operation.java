package com.example.pmsu_2019_projekat.model;

public enum Operation {

    MOVE,
    COPY,
    DELETE;

    public static int toInt(Operation operation) {
        switch (operation) {
            case MOVE:
                return 0;
            case COPY:
                return 1;
            default:
                return 2;
        }
    }

}
