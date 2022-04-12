package com.globallogic.users.restcontrollers;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.users.controllers.UserController;
import com.globallogic.users.dto.UserDataResponseLogin;
import com.globallogic.users.dto.UserRequestDTO;
import com.globallogic.users.dto.UserResponseDTO;
import com.globallogic.users.exceptions.UserEmailAlreadyExistsException;
import com.globallogic.users.services.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

	@Autowired
	UserController controller;

	@Autowired
	UserService service;

	@PostMapping("/sign-up")
	public ResponseEntity<?> create(@Valid @RequestBody UserRequestDTO userRequestDTO) throws UserEmailAlreadyExistsException {
		return new ResponseEntity<UserResponseDTO>(this.controller.create(userRequestDTO), HttpStatus.CREATED);
	}

	@GetMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getUserByToken(@RequestAttribute("id") UUID id) throws Exception {
		return new ResponseEntity<UserDataResponseLogin>(this.controller.getUserById(id), HttpStatus.OK);
	}
}
