package com.learn.english.geapp.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UUserRepository extends JpaRepository<UUser, String> {
	
}
