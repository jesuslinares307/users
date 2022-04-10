package com.globallogic.users.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globallogic.users.exceptions.UserNotFoundException;
import com.globallogic.users.model.User;
import com.globallogic.users.model.UserDataResponseLogin;
import com.globallogic.users.model.UserRequestDTO;
import com.globallogic.users.model.UserResponseDTO;
import com.globallogic.users.respositories.IUserRepository;
import com.globallogic.users.security.GeneratorJWT;

@Service
public class UserService implements IUserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IUserRepository repository;

	@Autowired
	private GeneratorJWT generatorJWT;

	@Override
	public UserDataResponseLogin getUserById(UUID id) throws UserNotFoundException {
		return this.modelMapper.map(this.repository.findById(id).orElseThrow(() -> new UserNotFoundException()),
				UserDataResponseLogin.class);
	}

	@Override
	public UserResponseDTO create(UserRequestDTO userRequestDTO) {

		User userSave = this.modelMapper.map(userRequestDTO, User.class);
		userSave.setCreated(LocalDateTime.now());
		userSave.setIsActive(true);
		userSave.setLastLogin(LocalDateTime.now());
		userSave = this.repository.save(userSave);
		userSave.setToken(this.generateTokenById(userSave.getId()));
		return this.modelMapper.map(userSave, UserResponseDTO.class);

	}

	@Override
	public List<UserResponseDTO> getAll() {
		return this.repository.findAll().stream().map(d -> this.modelMapper.map(d, UserResponseDTO.class))
				.collect(Collectors.toList());
	}

	// Genera el token mediante el UUID y actualiza el token por ID.
	public String generateTokenById(UUID id) {
		String token = this.generatorJWT.getJWTToken(id);
		this.repository.updateTokenById(token, id);
		return token;
	}

}
