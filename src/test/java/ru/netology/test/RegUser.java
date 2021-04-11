package ru.netology.test;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegUser {
    private String login;
    private String password;
    private String status;
}
