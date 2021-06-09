package com.newspoint.dbService;

import com.newspoint.entity.User;
import com.newspoint.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import java.io.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public void saveUser (User user) {
        repository.save(user);
    }

    public long getNumberOfUsers  () {
        return repository.count();
    }

}
