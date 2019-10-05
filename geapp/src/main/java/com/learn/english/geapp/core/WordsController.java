package com.learn.english.geapp.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.english.geapp.dto.CheckResult;
import com.learn.english.geapp.dto.ComboResult;
import com.learn.english.geapp.dto.WordInsert;

@RestController
public class WordsController {
	
	@Autowired
	WordsService wordsService;
	
	private static final String SEPARATOR = ":";

	@PostMapping(value="/check-words", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public CheckResult getResult(@RequestBody Map<String, String> mappa){
		
		WordInsert wordInsert = new WordInsert(mappa);
		return wordsService.getResult(wordInsert);
	}

	@PutMapping(value="/insert-words", consumes=MediaType.APPLICATION_JSON_VALUE)
	public String insertWords(@RequestBody Map<String, String> mappa) {

		return wordsService.insertWords(new WordInsert(mappa));
	}
	
	@GetMapping(value="/insert-words-from-file")
	public String insertWordsFromFile() throws IOException {

		String file = "src/main/resources/sourceFile.txt";
		FileReader in = new FileReader(file);
		BufferedReader reader = new BufferedReader(in);
		Map<String, String> mappa = new HashMap<>();
		
		String line;
		while((line = reader.readLine()) != null) {
			mappa.put(line.substring(0, line.indexOf(SEPARATOR)), line.substring(line.indexOf(SEPARATOR)+1));
		}
		
		in.close();
		return wordsService.insertWords(new WordInsert(mappa));
	}
	
	@PostMapping(value="/find-random-words", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<String> findRandomWords(@RequestParam String limit){
		return wordsService.findRandomWords(limit);
	}
	
	@PostMapping("/performance")
	public List<ComboResult> findPerformanceResult(@RequestParam String order, @RequestParam Integer limit){
		return wordsService.findPerformanceResult(order, limit );
	}
}
