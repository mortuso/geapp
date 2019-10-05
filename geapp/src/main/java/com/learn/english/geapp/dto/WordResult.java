package com.learn.english.geapp.dto;

import java.util.Map;

import com.learn.english.geapp.common.ResultEnumeration;

/**
 * Lista di chiavi legati al risultato
 */
public class WordResult {
	
	private Map<String,ResultEnumeration>result;
	
	public WordResult(Map<String,ResultEnumeration> result) {
		this.result = result;
	}

	public Map<String, ResultEnumeration> getResult() {
		return result;
	}

	public void setResult(Map<String, ResultEnumeration> result) {
		this.result = result;
	}
}
