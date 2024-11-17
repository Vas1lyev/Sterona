package ru.vasilyev.dbp.Sterona.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Role {

    private int id;

    @NotEmpty(message = "Название роли не должно быть пустым")
    @Size(min = 1, max = 100, message = "Название роли должно быть от 1 до 100 символов")
    private String name;

    public Role() {

    }

    public Role(int id, String name) {
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

