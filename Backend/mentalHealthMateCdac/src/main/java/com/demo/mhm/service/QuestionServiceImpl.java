package com.demo.mhm.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.mhm.dao.SiteGeneratedReportRepo;
import com.demo.mhm.dao.UserRepo;
import com.demo.mhm.model.SiteGeneratedReport;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class QuestionServiceImpl implements QuestionServiceI{
	@Autowired
	private SiteGeneratedReportRepo sgrr;
	@Autowired
	private UserRepo ur;
	
	
	@Override
	public boolean saveAnswer(int id,SiteGeneratedReport sgr) {
	sgr.setDate(new Date());
	sgr.setUser4(ur.getById(id));
	
	sgrr.save(sgr);
	return true;
}
	@Override
	public SiteGeneratedReport generateReport(int id) {
		System.out.println(sgrr.getByUserId(id));
		return sgrr.getByUserId(id);
	}
	
}