package com.viv.test.domain;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class UserDetails {
	
	private Integer id;
	@Size(min = 2,message = "you have to entered minimum 2 character in your name")
	private String name;
	@Past(message = "please enter  valid birth date")
	private Date birthDate;

}
