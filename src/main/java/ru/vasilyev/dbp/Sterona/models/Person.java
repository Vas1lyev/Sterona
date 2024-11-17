package ru.vasilyev.dbp.Sterona.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class Person {
    private int id;

    @NotEmpty(message = "Название доски не должно быть пустым")
    @Size(min = 1, max = 255, message = "Имя должно быть от 1 до 255 символов")
    private String username;

    @NotEmpty(message = "Email не должен быть быть пустым")
    @Size(min = 1, max = 100, message = "Email должен быть от 1 до 100 символов")
    @Email(message = "Email должен быть существующим")
    private String email;

    private Date dateJoined;
    private int organizationId;
    private int roleId;

    @NotEmpty(message = "Пароль не должен быть пустым")
    private String password;

    public Person() {

    }

    public Person(int id, String username, String email,
                  Date dateJoined, int organizationId,
                  int roleId, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.dateJoined = dateJoined;
        this.organizationId = organizationId;
        this.roleId = roleId;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDateJoined() {
        return dateJoined;
    }
    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }
    public int getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
