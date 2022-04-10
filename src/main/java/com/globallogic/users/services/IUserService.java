package com.globallogic.users.services;

import java.util.List;
import java.util.UUID;

import com.globallogic.users.exceptions.UserNotFoundException;
import com.globallogic.users.model.UserDataResponseLogin;
import com.globallogic.users.model.UserRequestDTO;
import com.globallogic.users.model.UserResponseDTO;

public interface IUserService {

	UserDataResponseLogin getUserById(UUID id) throws UserNotFoundException;

	UserResponseDTO create(UserRequestDTO userRequestDTO);

	List<UserResponseDTO> getAll();

}