package com.learn.english.geapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class WordDTO {
	
	private String wName;
	private String meaning;
	
	@JsonInclude(Include.NON_NULL)
	private String note;
	public String getwName() {
		return wName;
	}
	public void setwName(String wName) {
		this.wName = wName;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public WordDTO(String wName, String meaning, String note) {
		super();
		this.wName = wName;
		this.meaning = meaning;
		this.note = note;
	}
	public WordDTO() {
		super();
	}
	
}
