package com.viv.test.domain;

import java.util.Date;

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
	private String name;
	private Date birthDate;

}
