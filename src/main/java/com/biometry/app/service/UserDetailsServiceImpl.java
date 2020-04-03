package com.biometry.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biometry.app.config.UserPrincipal;
import com.biometry.app.entity.User;
import com.biometry.app.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if(user!=null) {
			
			UserPrincipal userPrincipal = new UserPrincipal(user);
			return userPrincipal;
		}else {
			throw new UsernameNotFoundException("username "+username+" not found ");
		}
	}
	
	
}
