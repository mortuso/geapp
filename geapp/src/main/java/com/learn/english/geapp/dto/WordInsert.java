package com.learn.english.geapp.dto;

import java.util.List;

/**
 * Lista di elementi legati al suo significato
 */
public class WordInsert {
	
	private List<WordDTO> input;
	
	public WordInsert(List<WordDTO> input) {
		super();
		this.input = input;
	}
	
	public WordInsert() {
	
	}

	public List<WordDTO> getInput() {
		return input;
	}

	public void setInput(List<WordDTO> input) {
		this.input = input;
	}
	
}