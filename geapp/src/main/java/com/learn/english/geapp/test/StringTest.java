package com.learn.english.geapp.test;

import java.io.IOException;

import com.learn.english.geapp.core.entity.Word;

public class StringTest {

	public static void main(String[] args) throws IOException {

		String parola = "nome : significato# questa è una nota";

		Word w = new Word();
		
		if(parola != null) {
			w.setwName(parola.substring(0, parola.indexOf(":")));
			w.setMeaning(parola.substring(parola.indexOf(":")+1,  parola.indexOf("#")));
			w.setNote(parola.substring(parola.indexOf("#")+1));
		}

		System.out.println(w.getwName() + w.getMeaning() + w.getNote());
	}

}
