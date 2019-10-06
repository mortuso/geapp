package com.learn.english.geapp.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.learn.english.geapp.dto.CheckResult;
import com.learn.english.geapp.dto.ComboResult;
import com.learn.english.geapp.dto.WordDTO;
import com.learn.english.geapp.dto.WordInsert;

@RestController
public class WordsController {
	
	@Autowired
	WordsService wordsService;
	
	private static final String SEPARATOR1 = ":";
	private static final String SEPARATOR2 = "#";

	/*
	 * controlla i valori inseriti dall'utente
	 */
	@PostMapping(value="/check-words", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public CheckResult getResult(@RequestBody List<WordDTO> values){
		
		return wordsService.getResult(new WordInsert(values));
	}

	/*
	 * permette di inserire una nuova tupla
	 */
	@PutMapping(value="/insert-words")
	public String insertWords(@RequestBody List<WordDTO> values) throws JsonParseException, JsonMappingException, IOException {
		
		return wordsService.insertWords(new WordInsert(values));
	}
	
	/*
	 * permette di inserire i dati nel db direttamente da un file sorgente
	 */
	@GetMapping(value="/insert-words-from-file")
	public String insertWordsFromFile() throws IOException {

		String file = "src/main/resources/sourceFile.txt";
		FileReader in = new FileReader(file);
		BufferedReader reader = new BufferedReader(in);

		List<WordDTO> words = new ArrayList<>();
		
		
		String line;
		while((line = reader.readLine()) != null) {
			
			WordDTO w = new WordDTO();
			
			w.setwName(line.substring(0, line.indexOf(SEPARATOR1)));
			w.setMeaning(line.substring(line.indexOf(SEPARATOR1)+1,  line.indexOf(SEPARATOR2)));
			w.setNote(line.substring(line.indexOf(SEPARATOR2)+1));
			
			if(w.getNote().isEmpty())
				w.setNote(null);
			
			words.add(w);
		}
		
		in.close();
		return wordsService.insertWords(new WordInsert(words));
	}
	
	/*
	 * è un quiz che viene proposto all'utente, è una lista di parole
	 */
	@PostMapping(value="/find-random-words", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<String> findRandomWords(@RequestParam String limit){
		return wordsService.findRandomWords(limit);
	}
	
	/*
	 * valutazione delle performance
	 */
	@PostMapping(value="/performance", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ComboResult> findPerformanceResult(@RequestParam String order, @RequestParam Integer limit){
		return wordsService.findPerformanceResult(order, limit );
	}
}
