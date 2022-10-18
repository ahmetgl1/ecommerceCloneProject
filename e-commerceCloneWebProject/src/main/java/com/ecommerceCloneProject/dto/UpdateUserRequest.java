package com.ecommerceCloneProject.dto;

public class UpdateUserRequest {


    private String firstName;
    private String middleName;
    private String lastName;
    private String mail;

    public UpdateUserRequest(String firstName, String middleName, String lastName, String mail) {
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
}
