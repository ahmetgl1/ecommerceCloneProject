package com.ecommerceCloneProject.dto;

import com.ecommerceCloneProject.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public UserDto convert(User user) {

        UserDto userDto = new UserDto(1L, "Ahmet", "Fatih", "Güzeller", "ahmetfatih.guzeller@pru.edu.tr", false);

        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        userDto.setMail(user.getMail());


        return userDto;
    }

    public List<UserDto> convert(List<User> userList){


        return userList.stream().map(user -> new UserDto(user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getMail())).collect(Collectors.toList());

    }


}
