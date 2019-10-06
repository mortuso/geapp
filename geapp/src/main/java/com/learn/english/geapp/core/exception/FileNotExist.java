package com.learn.english.geapp.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileNotExist extends RuntimeException {
	
	public FileNotExist(String message) {
		super(message);
	}
	
	public FileNotExist() {
		super();
	}
}