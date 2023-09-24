package com.loanapp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.loanapp.DTO.LoginDTO;
import com.loanapp.DTO.UserDTO;
import com.loanapp.entity.User;
import com.loanapp.repository.UserRepository;
import com.loanapp.response.LoginMessage;
import com.loanapp.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public String addUser(UserDTO userDTO) {
		User user = new User(
				userDTO.getUserId(),userDTO.getName(),userDTO.getUsername(),this.passwordEncoder.encode(userDTO.getPassword()));
		 userRepository.save(user);
		
		return user.getName();
	}
	
	UserDTO userdto;

	@Override
	public LoginMessage loginUser(LoginDTO loginDTO) {
		
		String message = "";
		
		User user1 = userRepository.findByUsername(loginDTO.getUsername());
		
		if (user1 != null) {
			String password = loginDTO.getPassword();
			String encodedPassword = user1.getPassword();
			Boolean isPswRight = passwordEncoder.matches(password, encodedPassword);
			
			if (isPswRight) {
				Optional<User> user = userRepository.findOneByUsernameAndPassword(loginDTO.getUsername(), encodedPassword);
				if (user.isPresent()) {
					return new LoginMessage("Login Success", true);
				}else {
					return new LoginMessage("Login Failed", false);
				}
			}else {
				return new LoginMessage("Password Not match !!!!", false);
			}
		}else 
		
		{
			return new LoginMessage("UserName Not exist!!!!!", false);
		}
	}

	
}
