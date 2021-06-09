package com.newspoint.entity;

import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity(name = "Users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "Id", unique = true)
    private Long id;

    @NonNull
    @Column(name = "First_Name")
    private String firstName;

    @NonNull
    @Column(name = "Last_Name")
    private String lastName;

    @NonNull
    @Column(name = "Birth_Date")
    private LocalDate birthDate;

    @Column(name = "Phone_Number", unique = true, length = 9)
    private String phoneNumber;
}
