package com.viv.test.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor

public class ExceptionResponseMessage {
	private Date timeStamp;
	private String message;
	private String details;

}
