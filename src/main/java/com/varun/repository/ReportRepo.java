package com.varun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.varun.entity.EligibilityDlts;

@Repository
public interface ReportRepo extends JpaRepository<EligibilityDlts, Integer> {

	@Query("select distinct planName from EligibilityDlts")
	public List<String> findPlanName();

	@Query("select distinct planStatus from EligibilityDlts")
	public List<String> findPlanStatus();

}
