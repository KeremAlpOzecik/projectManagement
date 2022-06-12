package com.epam.guest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "_USER")
@Setter
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "STATUS")

    private Boolean status;

    @Column(name = "NAME")
    @NotNull

    private String name;

    @Column(name = "EMAIL")
    @NotNull

    private String email;


    @Column(name = "USER_NAME")
    @NotNull
    private String userName;

    @Column(name = "PASSWORD")
    @NotNull

    private String password;
}
