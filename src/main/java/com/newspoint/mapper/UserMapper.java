package com.newspoint.mapper;

import com.newspoint.entity.User;
import com.newspoint.entity.UserDto;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User mapToUser (final UserDto userDto) {
        return new User(userDto.getId(),
                        userDto.getFirstName(),
                        userDto.getLastName(),
                        userDto.getBirthDate(),
                        userDto.getPhoneNumber());
    }

    public UserDto mapToUserDto (final User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getPhoneNumber());
    }

    public List<UserDto> mapToUserDtoList (final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
