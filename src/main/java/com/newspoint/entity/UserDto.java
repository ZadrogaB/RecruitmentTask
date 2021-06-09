package com.newspoint.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String phoneNumber;
}
