package com.demo.mhm.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.mhm.model.Users;
@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
//	int id, String username, String name, String email, int age, String phoneno, Address address,
//	String gender, String password, List<Appointment> appointment, List<FeedBack> feedback,
//	MedicalHistory diagnosis
	
	//@Query(value="select * from product  where price between :lpr and :hpr",nativeQuery = true)
	//@Query(select user from Users user where user.username)
	@Query("select u from Users u where u.username =?1")
	Users FindByUsername(String uname);
	@Query(value="select * from user_table where name=:patientName",nativeQuery=true)
	Users getByName(String patientName);
	@Query(value="select * from user_table where id=:id",nativeQuery=true)
	Users getById(int id);
	
}