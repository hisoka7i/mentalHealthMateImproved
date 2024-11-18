package com.demo.mhm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="DocPayment_table")
public class DoctorPayment {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int paymentId;
private double amount;
private boolean payStatus;
@OneToOne
private DoctorAppointment dPayment;
public DoctorPayment(int paymentId, double amount, boolean pay_status, DoctorAppointment dpayment) {
	super();
	this.paymentId = paymentId;
	this.amount = amount;
	this.payStatus = pay_status;
	this.dPayment = dpayment;
}
public DoctorPayment() {
	super();
}
public int getPaymentId() {
	return paymentId;
}
public void setPaymentId(int paymentId) {
	this.paymentId = paymentId;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public boolean isPay_status() {
	return payStatus;
}
public void setPay_status(boolean pay_status) {
	this.payStatus = pay_status;
}
public DoctorAppointment getDpayment() {
	return dPayment;
}
public void setDpayment(DoctorAppointment dpayment) {
	this.dPayment = dpayment;
}
@Override
public String toString() {
	return "Doctor_payment [paymentId=" + paymentId + ", amount=" + amount + ", pay_status=" + payStatus
			+ ", dpayment=" + dPayment + "]";
}

	
}