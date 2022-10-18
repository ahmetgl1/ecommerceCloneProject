package com.ecommerceCloneProject.service;

import com.ecommerceCloneProject.dto.CreateUserRequest;
import com.ecommerceCloneProject.dto.UpdateUserRequest;
import com.ecommerceCloneProject.dto.UserDto;
import com.ecommerceCloneProject.dto.UserDtoConverter;
import com.ecommerceCloneProject.entity.User;
import com.ecommerceCloneProject.exception.UserIsNotActiveException;
import com.ecommerceCloneProject.exception.UserNotFoundException;
import com.ecommerceCloneProject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    protected User findByUserMail(String mail) {

        return userRepository.findByMail(mail)
                .orElseThrow(() -> new UserNotFoundException("User Not FOUND !"));
    }


    public User findByUserId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not FOUND !"));


    }

    public List<UserDto> userGetAll() {

        return userDtoConverter.convert(userRepository.findAll());
       //userList.stream()
       //        .map(user -> new UserDto(user.getFirstName() ,
       //                user.getMiddleName(),
       //                user.getLastName(),
       //                user.getMail()
       //        ))
       //        .collect(Collectors.toList());

    }

    public UserDto userFindByGetMail(String mail) {
        User user = findByUserMail(mail);

        return userDtoConverter.convert(user);
    }

    public UserDto userCreate(CreateUserRequest createUserDtoRequest) {
      //stream kullanarak geliştirilme yapılacak !
        User user = new User();

        user.setFirstName(createUserDtoRequest.getFirstName());
        user.setMiddleName(createUserDtoRequest.getMiddleName());
        user.setLastName(createUserDtoRequest.getLastName());
        user.setMail(createUserDtoRequest.getMail());
        user.setActive(false);


        return userDtoConverter.convert(userRepository.save(user));

    }


    public UserDto updateUser(String mail, UpdateUserRequest request) {

        User user = findByUserMail(mail);

        if (!user.getActive()) {

            throw new UserIsNotActiveException("The user wanted update is not active");

        } else {


            user.setFirstName(request.getFirstName());
            user.setMiddleName(request.getMiddleName());
            user.setLastName(request.getLastName());
            user.setMail(request.getMail());
            user.getActive();
        }

        return userDtoConverter.convert(userRepository.save(user));

    }

    public void deActiveUser(Long id) {

        User user = findByUserId(id);

        changeActivateUser(id, false);

    }

    public void activeUser(Long id) {

        User user = findByUserId(id);

        changeActivateUser(id, true);

    }

    public void changeActivateUser(Long id, Boolean isActive) {


        User user = findByUserId(id);

        User request = new User();
        request.setFirstName(user.getFirstName());
        request.setMiddleName(user.getMiddleName());
        request.setLastName(user.getLastName());
        request.setMail(user.getMail());
        request.setActive(isActive);

        userRepository.save(request);

    }

    private boolean isUserExistById(Long id) {

        return userRepository.existsById(id);


    }

    public void deleteUser(Long id) {

        if (isUserExistById(id)) {

            userRepository.deleteById(id);

        } else {
            throw new UserNotFoundException("User İs Not Found !!!!!");

        }

    }







}





