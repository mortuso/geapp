package com.learn.english.geapp.core;

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

	public Word() {
	}

	public Word(String wName, String meaning) {
		super();
		this.wName = wName;
		this.meaning = meaning;
	}

	public String getWName() {
		return this.wName;
	}

	public void setWName(String wName) {
		this.wName = wName;
	}

	public String getMeaning() {
		return this.meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

}