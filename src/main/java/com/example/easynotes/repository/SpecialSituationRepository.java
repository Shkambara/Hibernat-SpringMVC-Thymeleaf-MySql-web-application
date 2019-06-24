package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.easynotes.model.SpecialSituation;

@Repository
public interface SpecialSituationRepository extends JpaRepository<SpecialSituation, Long> {

}
