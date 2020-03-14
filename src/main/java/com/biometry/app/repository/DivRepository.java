package com.biometry.app.repository;

import com.biometry.app.entity.Division;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DivRepository extends JpaRepository<Division,Integer> {
	Optional<Division> findByDivName(String divName);
	Optional<Division> findByDivId(Integer divId);
}
