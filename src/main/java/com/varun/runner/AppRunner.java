package com.varun.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.varun.entity.EligibilityDlts;
import com.varun.repository.ReportRepo;

@Component
public class AppRunner implements ApplicationRunner {

	@Autowired
	private ReportRepo repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		EligibilityDlts entity1 = new EligibilityDlts();

		entity1.setId(1);
		entity1.setName("Varun");
		entity1.setEmail("varun@gmail.com");
		entity1.setMobilenumber(9582415);
		entity1.setPlanName("SNAP");
		entity1.setGender('M');
		entity1.setSsn(2343444);
		entity1.setPlanStatus("Approved");

		repo.save(entity1);

		EligibilityDlts entity2 = new EligibilityDlts();

		entity2.setId(2);
		entity2.setName("Manish");
		entity2.setEmail("Manish@gmail.com");
		entity2.setMobilenumber(1222415);
		entity2.setPlanName("HEALTH");
		entity2.setGender('M');
		entity2.setSsn(2341111);
		entity2.setPlanStatus("closed");

		repo.save(entity2);

		EligibilityDlts entity3 = new EligibilityDlts();

		entity3.setId(3);
		entity3.setName("Akash");
		entity3.setEmail("Akash@gmail.com");
		entity3.setMobilenumber(9582415);
		entity3.setPlanName("ACAP");
		entity3.setGender('M');
		entity3.setSsn(234333);
		entity3.setPlanStatus("Denied");

		repo.save(entity3);

	}

}
