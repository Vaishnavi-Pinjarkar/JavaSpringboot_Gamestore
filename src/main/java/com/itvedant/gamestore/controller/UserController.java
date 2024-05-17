package com.itvedant.gamestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itvedant.gamestore.dao.LoginDao;
import com.itvedant.gamestore.dao.RegisterDao;
import com.itvedant.gamestore.dao.UpdateUserDao;
import com.itvedant.gamestore.entity.User;
import com.itvedant.gamestore.service.UserService;

//@RestController
@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private AuthenticationManager AuthenticationManager;
	
	@Autowired
	private UserService userService;
	
	/*
	
	@PostMapping("/reg")
	public ResponseEntity<?> register(@RequestBody RegisterDao registerDao){
		return ResponseEntity.ok(this.userService.register(registerDao));
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDao loginDao){
		Authentication authentication = AuthenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDao.getEmail(),
						                                loginDao.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return ResponseEntity.ok("User Logged In!!");
	}
	*/
	
	@GetMapping("/reg")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register.html";
	}
	
	@PostMapping("/reg")
	public String reg(Model model,@ModelAttribute("user") RegisterDao registerDao) {
		model.addAttribute("user", registerDao);
		this.userService.register(registerDao);
		return "login.html";
	}
	@GetMapping("/login")
	public String log(Model model) {
		model.addAttribute("user",new User());
		return "login";
		
	}
	@PostMapping("/login")
	public String login(Model model,@ModelAttribute("user") LoginDao loginDao) {
		model.addAttribute("user", loginDao);
		Authentication authentication = AuthenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDao.getEmail(),
						                                loginDao.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:index";
	}
	@GetMapping("")
	public String getAll(Model model) {
		model.addAttribute("users",this.userService.getAllUser());
		return "showusers.html";
	}
	@GetMapping("/useredit/{id}")
	public String editUser(Model model,@PathVariable("id") Integer id) {
		model.addAttribute("user", this.userService.findUserById(id));
		return "updateuser.html";
	}
	
	@PostMapping("/useredit/{id}")
	public String editUser(Model model,@ModelAttribute("users") UpdateUserDao userUpadateDao,@PathVariable("id") Integer id) {
		User user = this.userService.findUserById(id);
		model.addAttribute("user", user);
		
		
		this.userService.updateUser(userUpadateDao, id);
		return "redirect:/users";
	}
	
	@GetMapping("/deleteuser/{id}")
	public String deleteUser(@PathVariable("id") Integer id) {
		this.userService.deleteUser(id);
		return "redirect:/users";
	}
	
	
	
	
}	
