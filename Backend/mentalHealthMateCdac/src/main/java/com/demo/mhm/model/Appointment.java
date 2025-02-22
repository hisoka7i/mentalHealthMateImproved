package com.demo.mhm.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="user_Appointment")
public class Appointment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private int uAppointmentId;
private String dName;
private String dphoneNo;
private Date time;
private boolean status=false;
@ManyToOne
private Users user2;


public Appointment() {
	super();
}
public Appointment(int uAppointmentId, String dName, String dphoneno, Date time, boolean status, Users user2) {
	super();
	this.uAppointmentId = uAppointmentId;
	this.dName = dName;
	this.dphoneNo = dphoneno;
	this.time = time;
	this.status = status;
	this.user2 = user2;
}

public int getuAppointmentId() {
	return uAppointmentId;
}
public void setuAppointmentId(int uAppointmentId) {
	this.uAppointmentId = uAppointmentId;
}
public String getdName() {
	return dName;
}
public void setdName(String dName) {
	this.dName = dName;
}
public String getDphoneno() {
	return dphoneNo;
}
public void setDphoneno(String dphoneno) {
	this.dphoneNo = dphoneno;
}
public Date getTime() {
	return time;
}
public void setTime(Date time) {
	this.time = time;
}
public boolean getStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}
public Users getUser2() {
	return user2;
}
public void setUser2(Users user2) {
	this.user2 = user2;
}
@Override
public String toString() {
	return "Appointment [uAppointmentId=" + uAppointmentId + ", dName=" + dName + ", dphoneno=" + dphoneNo + ", time="
			+ time + ", status=" + status + ", user2=" + user2 + "]";
}


}
