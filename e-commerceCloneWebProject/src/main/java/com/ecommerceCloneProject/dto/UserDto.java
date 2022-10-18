package com.ecommerceCloneProject.dto;

import java.util.Objects;

public class UserDto {


    private String firstName;
    private String middleName;
    private String lastName;
    private String mail;


    public UserDto(long l, String ahmet, String fatih, String güzeller, String s, boolean b) {
    }

    public UserDto( String firstName, String middleName, String lastName, String mail) {

        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mail = mail;
    }

    public UserDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(firstName, userDto.firstName) && Objects.equals(middleName, userDto.middleName) && Objects.equals(lastName, userDto.lastName) && Objects.equals(mail, userDto.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName, mail);
    }
}

