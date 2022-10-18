package com.ecommerceCloneProject.controller;

import com.ecommerceCloneProject.dto.CreateUserRequest;
import com.ecommerceCloneProject.dto.UpdateUserRequest;
import com.ecommerceCloneProject.dto.UserDto;
import com.ecommerceCloneProject.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAllUser(){

        return ResponseEntity.ok(userService.userGetAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> userGetById(@PathVariable String mail){

        return ResponseEntity.ok(userService.userFindByGetMail(mail));
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserDtoRequest){

        return ResponseEntity.ok(userService.userCreate(createUserDtoRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String mail ,
                                              @RequestBody UpdateUserRequest updateUserRequest){

        return ResponseEntity.ok(userService.updateUser(mail , updateUserRequest));
    }



      @PatchMapping("/deactive/{id}")
    public ResponseEntity<Void> deactiveUser(@PathVariable Long id){

        userService.changeActivateUser(id , false);
        return ResponseEntity.ok().build();
      }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){

        userService.deleteUser(id);

        return ResponseEntity.ok().build();
    }


  @PatchMapping("/active/{id}")
   public ResponseEntity<Void> activeUser(@PathVariable Long id){

      userService.changeActivateUser(id , true);

      return ResponseEntity.ok().build();


    }






}
