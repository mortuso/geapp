package com.learn.english.geapp.core.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the word database table.
 * 
 */
@Entity
@NamedQuery(name="Word.findAll", query="SELECT w FROM Word w")
public class Word implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="w_name")
	private String wName;

	private String meaning;
	
	private String note;

	public Word() {
	}

	public Word(String wName, String meaning, String note) {
		super();
		this.wName = wName;
		this.meaning = meaning;
		this.note = note;
	}

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

}