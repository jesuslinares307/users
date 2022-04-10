package com.globallogic.users.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.globallogic.users.exceptions.UserNotFoundException;
import com.globallogic.users.model.UserDataResponseLogin;
import com.globallogic.users.model.UserRequestDTO;
import com.globallogic.users.model.UserResponseDTO;
import com.globallogic.users.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	public UserResponseDTO create(UserRequestDTO userRequestDTO) {
		return this.service.create(userRequestDTO);
	}

	public List<UserResponseDTO> getAll() {
		return this.service.getAll();
	}

	public UserDataResponseLogin getUserById(UUID id) throws UserNotFoundException {
		return this.service.getUserById(id);
	}

}
