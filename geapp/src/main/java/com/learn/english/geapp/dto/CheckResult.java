package com.learn.english.geapp.dto;

public class CheckResult {
	
	private WordResult wordResult;
	private WordInsert correctResult;
	private String result;
	
	public WordResult getWordResult() {
		return wordResult;
	}
	public void setWordResult(WordResult wordResult) {
		this.wordResult = wordResult;
	}
	public WordInsert getCorrectResult() {
		return correctResult;
	}
	public void setCorrectResult(WordInsert correctResult) {
		this.correctResult = correctResult;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
