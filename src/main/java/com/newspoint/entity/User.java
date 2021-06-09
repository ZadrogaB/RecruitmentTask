package com.newspoint.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Entity(name = "Users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false, unique = true)
    private Long id;

    @Column(name = "First_Name", nullable = false)
    private String firstName;

    @Column(name = "Last_Name", nullable = false)
    private String lastName;

    @Column(name = "Birth_Date", nullable = false)
    private String birthDate;

    @Column(name = "Phone_Number", unique = true, length = 9)
    private String phoneNumber;
}
