package com.globallogic.users.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.globallogic.users.dto.UserDataResponseLogin;
import com.globallogic.users.exceptions.UserEmailAlreadyExistsException;
import com.globallogic.users.exceptions.UserNotFoundException;
import com.globallogic.users.model.User;
import com.globallogic.users.repositories.UserRepository;
import com.globallogic.users.security.GeneratorJWT;

@Service
public class UserService {
	private ModelMapper modelMapper;
	private UserRepository userRepository;
	private GeneratorJWT generatorJWT;
	private PasswordEncoder passwordEncoder;

	public UserService(
			ModelMapper modelMapper,
			UserRepository repository,
			GeneratorJWT generatorJWT,
			PasswordEncoder passwordEncoder) {
		this.modelMapper = modelMapper;
		this.userRepository = repository;
		this.generatorJWT = generatorJWT;
		this.passwordEncoder = passwordEncoder;
	}

	public UserDataResponseLogin getUserById(UUID id) {
		Optional<User> userOpt = userRepository.findById(id);
		User user = null;
		if (userOpt.isPresent()) {
			user = userOpt.get();
			user.setLastLogin(LocalDateTime.now());
		} else {
			throw new UserNotFoundException();
		}
		return modelMapper.map(user, UserDataResponseLogin.class);
	}

	public User create(User user) {
		if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
			throw new UserEmailAlreadyExistsException();
		}

		user.setCreated(LocalDateTime.now());
		user.setIsActive(true);
		user.setLastLogin(null);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		User savedUser = this.userRepository.save(user);
		savedUser.setToken(this.generateTokenById(savedUser.getId()));

		return savedUser;
	}

	// Genera el token mediante el UUID y actualiza el token por ID.
	private String generateTokenById(UUID id) {
		String token = generatorJWT.getJWTToken(id);
		userRepository.updateTokenById(token, id);
		return token;
	}

}
