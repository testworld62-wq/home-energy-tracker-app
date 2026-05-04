package com.codominator.userservice.controller;


import com.codominator.userservice.dto.UserDto;
import com.codominator.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/create")
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
       return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    ResponseEntity<UserDto> createUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }


    @PostMapping("/update/{id}")
    ResponseEntity<UserDto> createUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    ResponseEntity<List<UserDto>> createUser() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
}
