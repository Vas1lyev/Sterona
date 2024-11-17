package ru.vasilyev.dbp.Sterona.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Organization {
    private int id;

    @NotEmpty(message = "Название организации не должно быть пустым")
    @Size(min = 1, max = 255, message = "Название организации должно быть от 1 до 255 символов")
    private String name;

    public Organization() {

    }

    public Organization(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
