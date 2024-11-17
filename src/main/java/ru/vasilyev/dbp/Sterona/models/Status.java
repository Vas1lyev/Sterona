package ru.vasilyev.dbp.Sterona.models;

import jakarta.validation.constraints.Size;

public class Status {
    private int id;

    @Size(min = 1, max = 255, message = "Email должен быть от 1 до 255 символов")
    private String name;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
