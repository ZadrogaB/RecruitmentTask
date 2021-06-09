package com.newspoint.controller;

import com.newspoint.dbService.UserService;
import com.newspoint.entity.User;
import com.newspoint.entity.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.util.*;

@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "uploadData")
    public void uploadData (FileReader csvFile) {

    }

    @GetMapping(value = "getNumberOfUsers")
    public int getNumberOfUsers () {
        int a = 1;
        return a;
    }

    @GetMapping(value = "getUsersSortedByAge")
    public List<UserDto> getUsersSortedByAge () {
        List<UserDto> result = new ArrayList<>();
        return result;
    }

    @GetMapping(value = "getOldestUserWithPhoneNumber")
    public UserDto getOldestUserWithPhoneNumber () {
        UserDto userDto = new UserDto();
        return userDto;
    }
}
