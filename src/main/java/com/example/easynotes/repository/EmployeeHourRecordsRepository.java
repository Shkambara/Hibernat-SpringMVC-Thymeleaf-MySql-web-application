package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.easynotes.model.EmployeeHourRecords;

public interface EmployeeHourRecordsRepository extends JpaRepository<EmployeeHourRecords, Long> {

}
