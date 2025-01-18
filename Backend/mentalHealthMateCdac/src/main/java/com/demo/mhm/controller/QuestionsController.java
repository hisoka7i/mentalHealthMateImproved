package com.demo.mhm.controller;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mhm.model.SiteGeneratedReport;
import com.demo.mhm.service.QuestionServiceI;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class QuestionsController {
	@Autowired
	QuestionServiceI qsi;
	@PostMapping("/question/{id}")
	public ResponseEntity<?> siteReportAnswers(@PathVariable("id") int id,@RequestBody SiteGeneratedReport sgr, Authentication authencation) {
		if(authencation.isAuthenticated())
			return ResponseEntity.ok(qsi.saveAnswer(id,sgr));	
		return ResponseEntity.status(HttpStatus.SC_FORBIDDEN).body(null);
	}
	@GetMapping("/question/sitereport/{id}")
	public ResponseEntity<?> getSiteGeneratedReport(@PathVariable("id") int id, Authentication authentication) {
		//return ResponseUtil.success(qsi.generateReport());
		if(authentication.isAuthenticated())
			return ResponseEntity.ok(qsi.generateReport(id));
		return ResponseEntity.status(HttpStatus.SC_FORBIDDEN).body(null);
	}
}
