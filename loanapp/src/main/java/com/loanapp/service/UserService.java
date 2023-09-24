package com.loanapp.service;

import com.loanapp.DTO.LoginDTO;
import com.loanapp.DTO.UserDTO;
import com.loanapp.response.LoginMessage;

public interface UserService {

	String addUser(UserDTO userDTO);
	LoginMessage loginUser(LoginDTO loginDTO);
}
