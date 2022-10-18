package com.ecommerceCloneProject.service;

import com.ecommerceCloneProject.dto.CreateUserRequest;
import com.ecommerceCloneProject.dto.UpdateUserRequest;
import com.ecommerceCloneProject.dto.UserDto;
import com.ecommerceCloneProject.dto.UserDtoConverter;
import com.ecommerceCloneProject.entity.User;
import com.ecommerceCloneProject.exception.UserIsNotActiveException;
import com.ecommerceCloneProject.exception.UserNotFoundException;
import com.ecommerceCloneProject.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest extends TestSupport{

    Long id = 1L;


    private UserService userService;

    private UserRepository userRepository;
    private UserDtoConverter userDtoConverter;



    @Before
    public void setUp() throws Exception {

        userRepository= Mockito.mock(UserRepository.class);
        userDtoConverter = Mockito.mock(UserDtoConverter.class);

        userService = new UserService(userRepository , userDtoConverter);

    }

     @Test
    public void getAllUsers_itShouldReturnUserDtoList(){

        List<User> userList = generateUserList();
        List<UserDto> userDtoList =generateUserDtoList(userList);


        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Mockito.when(userDtoConverter.convert(userList)).thenReturn(userDtoList);

        List<UserDto> result = userService.userGetAll();

         Assert.assertEquals(result , userDtoList);


         Mockito.verify(userRepository).findAll();
         Mockito.verify(userDtoConverter).convert(userList);

     }

     @Test
   public void testFindByUserMail_whenUserMailExistShouldReturnUser(){

        User user = generateUserFindByMail(mail);
        UserDto userDto = generateUserDtoFindByMail(user);

        Mockito.when(userRepository.findByMail(mail)).thenReturn(Optional.of(user));

        User result = userService.findByUserMail(mail);

        Assert.assertEquals(result , user);

        Mockito.verify(userRepository).findByMail(mail);

     }

     @Test
    public void testFindByUserMail_whenUserMailDoesNotExistShouldReturnThrowIsNotException(){

         User user = generateUserFindByMail(mail);
         UserDto userDto = generateUserDtoFindByMail(user);

         Mockito.when(userRepository.findByMail(mail)).thenReturn(Optional.empty());

          assertThrows(UserNotFoundException.class, () ->
                 userService.findByUserMail(mail));

          Mockito.verify(userRepository).findByMail(mail);
          Mockito.verifyNoInteractions(userDtoConverter);

     }

     @Test
     public void testCreateUser_itShouldReturnCreatedUserDto(){

         CreateUserRequest request = new CreateUserRequest("Ahmet", "Fatih", "Güzeller",mail);
         UserDto userDto = new UserDto("Ahmet", "Fatih", "Güzeller",mail);
         User savedUser = new User(1L,"Ahmet", "Fatih", "Güzeller",mail ,false);
          User user = new User("Ahmet", "Fatih", "Güzeller",mail,false);

          Mockito.when(userRepository.save(user)).thenReturn(savedUser);
          Mockito.when(userDtoConverter.convert(savedUser)).thenReturn(userDto);

          UserDto result  = userService.userCreate(request);

          Assert.assertEquals(result , userDto);

          Mockito.verify(userRepository).save(user);
          Mockito.verify(userDtoConverter).convert(savedUser);

    }


  @Test
  public void testUpdateUser_itShouldReturnUpdateUserDto(){

      UpdateUserRequest request = new UpdateUserRequest("Ahmet2", "Fatih2", "Güzeller2",mail);
      User user = new User("Ahmet", "Fatih", "Güzeller",mail,true);
    User savedUser = new User(1L,"Ahmet2", "Fatih2", "Güzeller2",mail,true);
    UserDto userDto = new UserDto("Ahmet", "Fatih", "Güzeller",mail);

    Mockito.when(userRepository.findByMail(mail)).thenReturn(Optional.of(user));

    Mockito.when(userRepository.save(user)).thenReturn(savedUser);
    Mockito.when(userDtoConverter.convert(savedUser)).thenReturn(userDto);


    UserDto result = userService.updateUser(mail , request);
   Assert.assertEquals(result , userDto);

       Mockito.verify(userRepository).findByMail(mail);
       Mockito.verify(userDtoConverter).convert(savedUser);


    }
     @Test
    public void testUpdateUser_whenUserMailExist_ItShouldReturnUserNotFoundException(){


        UpdateUserRequest request = new UpdateUserRequest("Ahmet2", "Fatih2", "Güzeller2",mail);
        User user = new User("Ahmet", "Fatih", "Güzeller",mail,true);

        Mockito.when(userRepository.findByMail(mail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class ,
                () -> userService.updateUser(mail , request));


         Mockito.verify(userRepository).findByMail(mail);
         Mockito.verifyNoMoreInteractions(userRepository);
          Mockito.verifyNoInteractions(userDtoConverter);

    }


    @Test
    public void testUpdateUser_whenUserMailExistButUserIsNotActive_itShouldReturnThrowIsNotActiveException(){

        UpdateUserRequest request = new UpdateUserRequest("Ahmet2", "Fatih2", "Güzeller2",mail);
        User user = new User("Ahmet", "Fatih", "Güzeller",mail,false);


        Mockito.when(userRepository.findByMail(mail)).thenReturn(Optional.of(user));

        assertThrows(UserIsNotActiveException.class , () -> userService.updateUser(mail , request));

        Mockito.verify(userRepository).findByMail(mail);
        Mockito.verifyNoInteractions(userDtoConverter);

    }


    //deactive user test edilecek !

}