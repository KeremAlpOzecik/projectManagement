package com.epam.guest.dto;

import com.googlecode.jmapper.annotations.JMap;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDetails {

	private Long id;
	private String userName;
	private String password;
}
