package com.ecommerceCloneProject.service;

import com.ecommerceCloneProject.dto.CreateUserRequest;
import com.ecommerceCloneProject.dto.UserDto;
import com.ecommerceCloneProject.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestSupport {

    String mail = "ahmetfatih.guzeller@pru.edu.tr";

    public static List<User> generateUserList() {

        UserDto userDto = new UserDto(1L,"Ahmet","Fatih","Güzeller","ahmetfatih.guzeller@pru.edu.tr",false);


        List<User> userList = new ArrayList<>();

        for (User user: userList) {
            userList.add(user);

        }
        return userList;


    }

    public static List<UserDto> generateUserDtoList(List<User> userList){

        return userList.stream().map(user -> new UserDto(user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getMail())
        ).collect(Collectors.toList());

    }
    public static User generateUserFindByMail(String mail){


        return new User(1L,
                "Ahmet",
                "Fatih",
                "Güzeller",
                mail,
                true);
    }

    public static UserDto generateUserDtoFindByMail(User user){


        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getMail(),
                user.getActive()

                );
    }
    public static UserDto generateCreateUserDto(CreateUserRequest createUserRequest){

         UserDto userDto = new UserDto();

         userDto.setFirstName(createUserRequest.getFirstName());
         userDto.setMiddleName(createUserRequest.getMiddleName());
         userDto.setLastName(createUserRequest.getLastName());
         userDto.setMail(createUserRequest.getMail());

         return userDto;

    }



}
