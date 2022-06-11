package com.epam.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseAuth<T> {
    private Long userId;
    private T data;
    private Date date;
    private String message;

}