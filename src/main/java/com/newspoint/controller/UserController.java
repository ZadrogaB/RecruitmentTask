package com.newspoint.controller;

import com.newspoint.Exception.UserNotExist;
import com.newspoint.dbService.UserService;
import com.newspoint.entity.User;
import com.newspoint.entity.UserDto;
import com.newspoint.mapper.UserMapper;
import com.newspoint.service.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserService service;
    private final CsvService csvService;
    private final UserMapper mapper;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService service, CsvService csvService, UserMapper mapper) {
        this.service = service;
        this.csvService = csvService;
        this.mapper = mapper;
    }

    @PostMapping(value = "uploadData")
    public void uploadData(@RequestParam MultipartFile csvFile) {
        try {
            csvService.addUsersFromFile(csvFile);
        } catch (IOException exception) {
            logger.error("IOException error. Cant read the file");
        }
    }

    @GetMapping(value = "getNumberOfUsers")
    public long getNumberOfUsers() {
        return service.getNumberOfUsers();
    }

    @GetMapping(value = "getUsersSortedByAge")
    public List<UserDto> getUsersSortedByAge() {
        List<User> listOfUsers = (List<User>) service.getAllUsers();
        List<UserDto> result = mapper.mapToUserDtoList(
                listOfUsers.stream()
                        .sorted(Comparator.comparing(user -> user.getBirthDate()))
                        .collect(Collectors.toList()));
        return result;
    }

    @GetMapping(value = "getOldestUserWithPhoneNumber")
    public UserDto getOldestUserWithPhoneNumber() {
        UserDto result = null;
        List<UserDto> sortedList = getUsersSortedByAge();
        for (UserDto userDto : sortedList) {
            result = userDto;
            if (!result.getPhoneNumber().isEmpty()) {
                logger.info("There is no such user");
            }
        }
        return result;
    }

    @DeleteMapping(value = "deleteUserById")
    public void deleteUserById(@RequestParam Long id) {
        try {
            service.deleteById(id);
        } catch (UserNotExist userNotExist) {
            logger.info("User with that ID doesn't exist");
        }
    }

    @DeleteMapping(value = "deleteUserListById", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUserListById(@RequestBody List<Long> ids) {
        ids.stream()
                .forEach(id -> deleteUserById(id));
    }

    @GetMapping(value = "findUserByLastname")
    public List<UserDto> findUserByLastname(@RequestParam String lastname) {
        List<User> result = (List <User>) service.findUserByLastname(lastname);
        return mapper.mapToUserDtoList(result);
    }

    @GetMapping(value = "findAllByLastNameStartsWith")
    public List<UserDto> findAllByLastNameStartsWith(@RequestParam String lastname) {
        List<User> result = (List <User>) service.findAllByLastNameStartsWith(lastname);
        return mapper.mapToUserDtoList(result);
    }
}
