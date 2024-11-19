package com.demo.mhm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.mhm.dao.UserRepo;
import com.demo.mhm.model.Users;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// System.out.println( "user details service is getting called with username " + username);
		Users user = userRepo.FindByUsername(username);
		if(user == null){
			// System.out.println(" We did not find the user");
			throw new UsernameNotFoundException("In valid username");
		}
		// System.out.println(user.getPassword() + " < This is password ."); // i am getting password here

		// TODO Auto-generated method stub
		return new CustomUserDetails(user);
	}

}
