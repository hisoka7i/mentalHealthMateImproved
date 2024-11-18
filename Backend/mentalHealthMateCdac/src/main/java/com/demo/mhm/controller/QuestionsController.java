package com.demo.mhm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mhm.model.SiteGeneratedReport;
import com.demo.mhm.service.QuestionServiceI;

@RestController
@CrossOrigin(origins="*")
public class QuestionsController {
	@Autowired
	QuestionServiceI qsi;
	@PostMapping("/question/{id}")
	public ResponseEntity<?> siteReportAnswers(@PathVariable("id") int id,@RequestBody SiteGeneratedReport sgr) {
		return ResponseEntity.ok(qsi.saveAnswer(id,sgr));	
	}
	@GetMapping("/question/sitereport/{id}")
	public ResponseEntity<?> getSiteGeneratedReport(@PathVariable("id") int id) {
		
		//return ResponseUtil.success(qsi.generateReport());
		return ResponseEntity.ok(qsi.generateReport(id));
	}
	
	
}
