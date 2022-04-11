package com.globallogic.users.controllers;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.globallogic.users.exceptions.UserNotFoundException;
import com.globallogic.users.model.User;
import com.globallogic.users.model.UserDataResponseLogin;
import com.globallogic.users.model.UserRequestDTO;
import com.globallogic.users.model.UserResponseDTO;
import com.globallogic.users.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private ModelMapper modelMapper;
	

	public UserResponseDTO create(UserRequestDTO userRequestDTO) {
		User savedUser = this.service.create(this.modelMapper.map(userRequestDTO, User.class));
		return this.modelMapper.map(savedUser, UserResponseDTO.class);
	}
	
	public UserDataResponseLogin getUserById(UUID id) throws UserNotFoundException {
		return this.service.getUserById(id);
	}

}
