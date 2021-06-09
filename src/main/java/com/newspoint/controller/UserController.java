package com.newspoint.controller;

import com.newspoint.dbService.UserService;
import com.newspoint.entity.UserDto;
import com.newspoint.service.CsvService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserService service;
    private final CsvService csvService;

    @Autowired
    public UserController(UserService service, CsvService csvService) {
        this.service = service;
        this.csvService = csvService;
    }

    @PostMapping(value = "uploadData")
    public void uploadData (@RequestParam MultipartFile csvFile) throws IOException {

        csvService.addUsersFromFile(csvFile);
    }

    @GetMapping(value = "getNumberOfUsers")
    public long getNumberOfUsers () {
        return service.getNumberOfUsers();
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
