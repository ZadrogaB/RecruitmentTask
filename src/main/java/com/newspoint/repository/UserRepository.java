package com.newspoint.repository;

import com.newspoint.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Iterable<User> findAllByLastName(String lastname);
    Iterable<User> findAllByLastNameStartsWith(String lastname);
}
