package com.demo.mhm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.mhm.dao.UserPaymentRepo;
import com.demo.mhm.model.UserPayment;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentServiceI{

	@Autowired
	UserPaymentRepo upr;
	
	
	@Override
	public List<UserPayment> findAllPayment() {
		// TODO Auto-generated method stub
		return upr.findAll();
	}

}
