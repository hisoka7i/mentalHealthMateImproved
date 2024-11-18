package com.demo.mhm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.mhm.dao.SiteGeneratedReportRepo;
import com.demo.mhm.model.SiteGeneratedReport;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class SiteGenReportImpl implements SiteGenReportI {
	
	@Autowired
	SiteGeneratedReportRepo sgr;

	@Override
	public List<SiteGeneratedReport> getAllReports(int id) {
		// TODO Auto-generated method stub
		return sgr.findAllById(id);
	}

	

}
