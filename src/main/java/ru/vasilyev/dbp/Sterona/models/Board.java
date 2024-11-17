package ru.vasilyev.dbp.Sterona.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Board {

    private int id;

    @NotEmpty(message = "Название доски не должно быть пустым")
    @Size(min = 1, max = 255, message = "Название доски должно быть от 1 до 255 символов")
    private String name;
    private int organizationId;

    public Board() {

    }

    public Board(int id, String name, int organizationId) {
        this.id = id;
        this.name = name;
        this.organizationId = organizationId;
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
    public int getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }
}
