package com.learn.english.geapp.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.english.geapp.common.ResultEnumeration;
import com.learn.english.geapp.core.entity.WResult;
import com.learn.english.geapp.core.entity.Word;
import com.learn.english.geapp.core.repository.WResultRepository;
import com.learn.english.geapp.core.repository.WordsRepository;
import com.learn.english.geapp.dto.CheckResult;
import com.learn.english.geapp.dto.ComboResult;
import com.learn.english.geapp.dto.WordDTO;
import com.learn.english.geapp.dto.WordInsert;
import com.learn.english.geapp.dto.WordResult;

@Service
public class WordsService {

	@Autowired
	WordsRepository wordsRepository;

	@Autowired
	WResultRepository wResultRepository;

	@Transactional
	public String insertWords(WordInsert wordInsert) {

		try {
			for(WordDTO w: wordInsert.getInput()) {

				Word word = new Word();
				if(w.getwName() != null)
					word.setwName(w.getwName().toLowerCase().trim());
				if(w.getMeaning() != null)
					word.setMeaning(w.getMeaning().toLowerCase().trim());
				if(w.getNote() != null)
					word.setNote(w.getNote().toLowerCase().trim());

				wordsRepository.save(word);
			}
		} catch (Throwable e) {
			return "Errore nell'inserimento";
		}
		return "Inserimento andato a buon fine";
	}

	@Transactional
	public CheckResult getResult(WordInsert wordInsert) {

		List<String> inputList = new ArrayList<>();

		//estraggo la chiave della  mappa e la metto in una lista di stringhe
		for(WordDTO input: wordInsert.getInput()) {
			inputList.add(input.getwName().trim().toLowerCase());
		}

		//ricerco tutte le entity che hanno quel nome, ottengo quindi una lista di entity
		List<Word> queryResult = wordsRepository.findByWNameIn(inputList);

		Map<String, ResultEnumeration> result = new HashMap<String, ResultEnumeration>();
		float resultValue = 0;

		// ciclo le due liste, confronto le chiavi uguali e vedo se hanno lo stesso valore
		for(WordDTO entry : wordInsert.getInput()) {
			result.put(entry.getwName(), ResultEnumeration.KO);
			for(Word word: queryResult) {
				if(entry.getwName().equalsIgnoreCase(word.getwName()) 
						&& entry.getMeaning().equalsIgnoreCase(word.getMeaning())) {
					result.put(entry.getwName(), ResultEnumeration.OK);
					resultValue += 1;
					break;
				}
			}
		}

		// tiro fuori la lista di tutti gli elementi che sono nella tabella dei risultati
		List<WResult> wResultQuery = wResultRepository.findByWNameIn(inputList);
		List<String> wResultQueryNames = wResultQuery.stream().map(e->e.getWName()).collect(Collectors.toList());

		for(Map.Entry<String, ResultEnumeration> entry: result.entrySet()) {
			WResult oldEntity = null;
			if(wResultQueryNames.contains(entry.getKey())) {
				oldEntity = wResultQuery.get(wResultQueryNames.indexOf(entry.getKey()));
			}

			WResult wResult = new WResult();

			wResult.setWName(entry.getKey());

			if(oldEntity != null) {
				wResult.setCentered(entry.getValue().equals(ResultEnumeration.OK) ? 
						oldEntity.getCentered() + 1 : oldEntity.getCentered() );
				wResult.setAttempts(oldEntity.getAttempts() + 1);
				BigDecimal a = BigDecimal.valueOf(wResult.getCentered());
				BigDecimal b = BigDecimal.valueOf(wResult.getAttempts());
				BigDecimal c = a.divide(b, 2, RoundingMode.CEILING).multiply(BigDecimal.valueOf(100));
				wResult.setPercent(c);
			} else {
				wResult.setCentered(entry.getValue().equals(ResultEnumeration.OK) ? 1 : 0 );
				wResult.setAttempts(1);
				BigDecimal a = BigDecimal.valueOf(wResult.getCentered());
				BigDecimal b = BigDecimal.valueOf(wResult.getAttempts());
				BigDecimal c = a.divide(b, 2, RoundingMode.CEILING).multiply(BigDecimal.valueOf(100));
				wResult.setPercent(c);
			}

			wResultRepository.save(wResult);
		}

		List<WordDTO> rightMeanings = new ArrayList<>();

		for(Map.Entry<String, ResultEnumeration> entry: result.entrySet()) {
			if(entry.getValue().equals(ResultEnumeration.KO)) {
				Word word = queryResult.stream().filter(e->e.getwName().equalsIgnoreCase(entry.getKey())).findAny().get();

				WordDTO wordDTO = new WordDTO(word.getwName(), word.getMeaning(), word.getNote());
				
				rightMeanings.add(wordDTO);
			}
		}

		CheckResult checkResult = new CheckResult();
		checkResult.setWordResult(new WordResult(result));
		checkResult.setCorrectResult(new WordInsert(rightMeanings));

		BigDecimal a = BigDecimal.valueOf(resultValue).divide(BigDecimal.valueOf(result.size())).
				multiply(BigDecimal.valueOf(100)).setScale(2);

		checkResult.setResult("Your result is "+(int)resultValue + " on " + result.size() + ". Your percent is "+ a +"%." );

		return checkResult;

	}


	public List<String> findRandomWords(String limit){
		return wordsRepository.findRandomWords(Integer.valueOf(limit)).stream().map(e->e.getwName()).collect(Collectors.toList());
	}



	public List<ComboResult> findPerformanceResult(String order, Integer limit) {

		List<WResult> result = new ArrayList<>();

		if(order.equalsIgnoreCase("asc")) {
			result = wResultRepository.findPerformanceResultAsc(limit);
		} else if (order.equalsIgnoreCase("desc")) {
			result = wResultRepository.findPerformanceResultDesc(limit);
		} else {
			throw new IllegalArgumentException(order + " is not a valid value");
		}

		List<String> resultNames = result.stream().map(e->e.getWName()).collect(Collectors.toList());
		List<Word> wordResult = wordsRepository.findByWNameIn(resultNames);

		List<ComboResult> comboResults = new ArrayList<>();

		for(Word word: wordResult) {
			for(WResult wres: result) {
				if(wres.getWName().equalsIgnoreCase(word.getwName())) {
					ComboResult entity = new ComboResult();
					entity.setAttempts(wres.getAttempts());
					entity.setMeaning(word.getMeaning());
					entity.setPercent(wres.getPercent().intValue());
					entity.setwName(word.getwName());
					entity.setNote(word.getNote());

					comboResults.add(entity);
				}
			}
		}
		return comboResults;
	}
}