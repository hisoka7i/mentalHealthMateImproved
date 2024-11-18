package com.demo.mhm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity()
@Table(name="User_Table")
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
private String username;
private String name;
private String email;
private Integer age;
private String phoneno;
private Character gender;
private String password;


public Users(Integer id, String username, String name, String email, Integer age, String phoneno, Character gender,
		String password) {
	super();
	this.id = id;
	this.username = username;
	this.name = name;
	this.email = email;
	this.age = age;
	this.phoneno = phoneno;
	this.gender = gender;
	this.password = password;
}



public Integer getId() {
	return id;
}



public void setId(Integer id) {
	this.id = id;
}



public String getUsername() {
	return username;
}



public void setUsername(String username) {
	this.username = username;
}



public String getName() {
	return name;
}



public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Integer getAge() {
	return age;
}

public void setAge(Integer age) {
	this.age = age;
}

public String getPhoneno() {
	return phoneno;
}

public void setPhoneno(String phoneno) {
	this.phoneno = phoneno;
}

public Character getGender() {
	return gender;
}

public void setGender(Character gender) {
	this.gender = gender;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}


@Override
public String toString() {
	return "Users [id=" + id + ", username=" + username + ", name=" + name + ", email=" + email + ", age=" + age
			+ ", phoneno=" + phoneno + ", gender=" + gender + ", password=" + password + "]";
}


public Users() {
	super();
}

}

