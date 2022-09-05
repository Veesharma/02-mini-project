package com.varun.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class EligibilityDlts {
	@Id
	@GeneratedValue
	@Column(name = "ELIG_ID")
	private Integer id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "GENDER")
	private Character gender;
	
	@Column(name = "MOBILE_NUMBER")
	private Integer mobilenumber;
	
	@Column(name = "SSN")
	private Integer ssn;
	
	@Column(name = "PLAN_NAME")
	private String planName;
	
	@Column(name = "PLAN_STATUS")
	private String planStatus;
	
	@Column(name = "PLAN_START_DATE")
	private LocalDate planStartDate;
	
	@Column(name = "PLAN_END_DATE")
	private LocalDate planEndDate;
	
	@Column(name = "PLAN_CRAETED_DATE")
	private LocalDate planCreatedDate;
	
	@Column(name = "PLAN_UPDATED_DATE")
	private LocalDate planUpdatedDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	
	
}
