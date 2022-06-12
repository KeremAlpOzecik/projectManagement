package com.epam.guest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordDto {
    private Long id;
    private String oldPassword;
    private String newPassword;
}
