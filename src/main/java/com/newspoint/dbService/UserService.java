package com.newspoint.dbService;

import com.newspoint.entity.User;
import com.newspoint.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public void saveUser(User user) {
        repository.save(user);
    }

    public long getNumberOfUsers() {
        return repository.count();
    }

    public Iterable<User> getAllUsers() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void deleteById(List<Long> ids) {
        repository.deleteAllById(ids);
    }

    public Iterable<User> findUserByLastname(String lastname) {
        return repository.findAllByLastName(lastname);
    }

    public Iterable<User> findAllByLastNameStartsWith(String lastname) {
        return repository.findAllByLastNameStartsWith(lastname);
    }


}
