package com.example.pmsu_2019_projekat.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Rule implements Serializable {
    private String id;
    private String  condition;
    private String operation;
    private ArrayList<Folder> folders;

    public Rule() {
    }

    public Rule(String id, String condition, String operation, ArrayList<Folder> folders) {
        this.id = id;
        this.condition = condition;
        this.operation = operation;
        this.folders = folders;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public ArrayList<Folder> getFolders() {
        return folders;
    }

    public void setFolders(ArrayList<Folder> folders) {
        this.folders = folders;
    }
}
