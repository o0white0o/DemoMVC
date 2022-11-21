package ru.github.o0white0o.demomvc.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;

public class Person {

    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 1, max = 10, message = "1 < Name < 15 chars")
    private String name;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 1, max = 15, message = "1 < Surname < 15 chars")
    private String surname;

    @Email
    private String email;

    public Person(int id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Person() {
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void update(Person person) {
        this.name = person.getName();
        this.surname = person.getSurname();
        this.email = person.getEmail();
    }
}
