package com.learn.english.geapp.test;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.learn.english.geapp.core.entity.Word;

public class JsonTest {
	
	public static void main(String[] args) throws JsonProcessingException {
		
		Word w1 = new Word("nome", "significato", "note");
		Word w2 = new Word("nome2", "significato2", "note2");
		
		List<Word> words = new ArrayList<>();
		words.add(w1);
		words.add(w2);
		

    	ObjectMapper objectMapper = new ObjectMapper();
    	//Set pretty printing of json
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    	
    	//1. Convert List of Person objects to JSON
    	String arrayToJson = objectMapper.writeValueAsString(words);
    	System.out.println("1. Convert List of person objects to JSON :");
    	System.out.println(arrayToJson);
    	
		
	}

}
