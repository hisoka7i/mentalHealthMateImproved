package com.demo.mhm.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.mhm.dao.DoctorPaymentRepo;
import com.demo.mhm.model.DoctorPayment;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DocPaymentServiceImpl implements DocPaymentServiceI {
	
	@Autowired
	DoctorPaymentRepo dpr;
	

	@Override
	public List<DoctorPayment> findAllappointment(int paymentId) {
		// TODO Auto-generated method stub
		return dpr.findAll();
	}

}
