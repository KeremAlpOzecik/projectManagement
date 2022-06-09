package com.taskify.projectservice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Long id;
    private Boolean status;
    private String name;
    private String email;
    private String userName;
    private String password;
}
