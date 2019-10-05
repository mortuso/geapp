package com.learn.english.geapp.core;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WordsRepository extends JpaRepository<Word, String> {
	
	public List<Word> findByWNameIn(List<String> name);
	
	@Query(value = "SELECT * FROM WORD w ORDER BY RANDOM() LIMIT ?1", nativeQuery = true)
	public List<Word> findRandomWords(Integer limit);

}
