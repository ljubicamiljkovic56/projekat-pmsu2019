package com.example.pmsu_2019_projekat.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Rule implements Serializable {
    private String id;
    private Condition condition;
    private Operation operation;

    public Rule() {
    }

    public Rule(String id, Condition condition, Operation operation) {
        this.id = id;
        this.condition = condition;
        this.operation = operation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

}
