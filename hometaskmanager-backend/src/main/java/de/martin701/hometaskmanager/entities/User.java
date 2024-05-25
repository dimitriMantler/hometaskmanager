package de.martin701.hometaskmanager.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class User extends GeneralEntity{
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String avatar;
    private LocalDateTime lastLogin;
}