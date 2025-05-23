package com.gonzalodev.saiyajinstore.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String userName;
    private String  firstName;
    private String lastName;
    private String email;
    private String password;
    private UserType userType;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
