package com.globallogic.users.controllers;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;

import com.globallogic.users.dto.UserDataResponseLogin;
import com.globallogic.users.dto.UserRequestDTO;
import com.globallogic.users.dto.UserResponseDTO;
import com.globallogic.users.exceptions.UserNotFoundException;
import com.globallogic.users.model.User;
import com.globallogic.users.services.UserService;

@Controller
public class UserController {
	private UserService service;
	private ModelMapper modelMapper;

	public UserController(
			UserService service, 
			ModelMapper modelMapper) {
		this.service = service;
		this.modelMapper = modelMapper;
	}

	public UserResponseDTO create(UserRequestDTO userRequestDTO) {
		User savedUser = this.service.create(this.modelMapper.map(userRequestDTO, User.class));
		return this.modelMapper.map(savedUser, UserResponseDTO.class);
	}

	public UserDataResponseLogin getUserById(UUID id) throws UserNotFoundException {
		return this.service.getUserById(id);
	}

}
