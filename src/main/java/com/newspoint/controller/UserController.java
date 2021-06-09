package com.newspoint.controller;

import com.newspoint.dbService.UserService;
import com.newspoint.entity.User;
import com.newspoint.entity.UserDto;
import com.newspoint.mapper.UserMapper;
import com.newspoint.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserService service;
    private final CsvService csvService;
    private final UserMapper mapper;

    @Autowired
    public UserController(UserService service, CsvService csvService, UserMapper mapper) {
        this.service = service;
        this.csvService = csvService;
        this.mapper = mapper;
    }

    @PostMapping(value = "uploadData")
    public void uploadData (@RequestParam MultipartFile csvFile) throws IOException, ParseException {
        csvService.addUsersFromFile(csvFile);
    }

    @GetMapping(value = "getNumberOfUsers")
    public long getNumberOfUsers () {
        return service.getNumberOfUsers();
    }

    @GetMapping(value = "getUsersSortedByAge")
    public List<UserDto> getUsersSortedByAge () {
        List<User> listOfUsers = (List<User>) service.getAllUsers();
        List<UserDto> result = mapper.mapToUserDtoList(
                listOfUsers.stream()
                        .sorted(Comparator.comparing(user -> user.getBirthDate()))
                        .collect(Collectors.toList()));
        return result;
    }

    @GetMapping(value = "getOldestUserWithPhoneNumber")
    public UserDto getOldestUserWithPhoneNumber () {
        UserDto result = null;
        List<UserDto> sortedList = getUsersSortedByAge();
        for (UserDto userDto : sortedList) {
            result = userDto;
            if (!result.getPhoneNumber().isEmpty()) {
                break;
            }
        }
        return result;
    }
}
