package com.itvedant.gamestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.itvedant.gamestore.dao.RegisterDao;
import com.itvedant.gamestore.dao.UpdateProductDao;
import com.itvedant.gamestore.entity.Product;
import com.itvedant.gamestore.entity.User;
import com.itvedant.gamestore.repository.UserRepository;
import com.itvedant.gamestore.dao.UpdateUserDao;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	public BCryptPasswordEncoder passwordEncoder;
	
	public User register(RegisterDao registerDao ) {
		
		
		User user = new User();
		user.setFirst_name(registerDao.getFirst_name());
		user.setLast_name(registerDao.getLast_name());
		user.setEmail(registerDao.getEmail());
		user.setPassword(passwordEncoder.encode(registerDao.getPassword()));
		user.setAddress(registerDao.getAddress());
		user.setRoles(registerDao.getRoles());
		
		
		this.userRepository.save(user);
		return user;
	}
	
	public Iterable<User> getAllUser(){
		return this.userRepository.findAll();
	}
	
	public User findUserById(Integer id) {
		User pro = this.userRepository.findById(id).orElse(null);
		
		
		if(pro == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"); 
			
		}
		return pro;
	}
	
	
	public User updateUser(UpdateUserDao updateUserDao, Integer id) {
		
		User user = this.findUserById(id);
		
		if(updateUserDao.getFirst_name() !=null) {
			user.setFirst_name(updateUserDao.getFirst_name());
		}
		
		if(updateUserDao.getLast_name() !=null) {
			user.setLast_name(updateUserDao.getLast_name());
		}
		
		if(updateUserDao.getEmail() !=null) {
			user.setEmail(updateUserDao.getEmail());
		}
		
		if(updateUserDao.getAddress() !=null) {
			user.setAddress(updateUserDao.getAddress());
		}
		if(updateUserDao.getRoles() !=null) {
			user.setRoles(updateUserDao.getRoles());
		}
		
		this.userRepository.save(user);
		
		return user;
		
		
	
	}
	
	public void deleteUser(Integer id) {
		User user = this.findUserById(id);
		
		if(user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User does not exist");
			
		}
		this .userRepository.deleteById(id);
	}
	

}