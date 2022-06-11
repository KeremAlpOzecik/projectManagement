package com.epam.guest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails extends User {
    private String address;
    private String city;
    private String country;
    private String phone;



}
