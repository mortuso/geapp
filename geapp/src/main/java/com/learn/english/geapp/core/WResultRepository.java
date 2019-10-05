package com.learn.english.geapp.core;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WResultRepository extends JpaRepository<WResult, String> {
	
	public List<WResult> findByWNameIn(List<String> name);
	
	@Query(value = "SELECT * FROM W_RESULT w ORDER BY PERCENT asc LIMIT ?1", nativeQuery = true)
	public List<WResult> findPerformanceResultAsc(Integer limit);

	@Query(value = "SELECT * FROM W_RESULT w ORDER BY PERCENT desc LIMIT ?1", nativeQuery = true)
	public List<WResult> findPerformanceResultDesc(Integer limit);
	
}
