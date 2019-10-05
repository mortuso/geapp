package com.learn.english.geapp.dto;

import java.util.Map;

/**
 * Lista di elementi legati al suo significato
 */
public class WordInsert {

	private Map<String,String> input;
	
	public WordInsert(Map<String,String> input) {
		this.input = input;
	}

	public Map<String, String> getInput() {
		return input;
	}

	public void setInput(Map<String, String> input) {
		this.input = input;
	}
}