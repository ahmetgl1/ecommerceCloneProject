package com.ecommerceCloneProject.dto;

import java.util.Objects;

public class CreateUserRequest {


    private String firstName;
    private String middleName;
    private String lastName;
    private String mail;


    public CreateUserRequest(String firstName, String middleName, String lastName, String mail) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mail = mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUserRequest request = (CreateUserRequest) o;
        return Objects.equals(firstName, request.firstName) && Objects.equals(middleName, request.middleName) && Objects.equals(lastName, request.lastName) && Objects.equals(mail, request.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName, mail);
    }
}
