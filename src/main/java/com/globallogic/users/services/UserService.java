package com.globallogic.users.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.globallogic.users.exceptions.UserEmailAlreadyExistiException;
import com.globallogic.users.exceptions.UserNotFoundException;
import com.globallogic.users.model.User;
import com.globallogic.users.model.UserDataResponseLogin;
import com.globallogic.users.respositories.IUserRepository;
import com.globallogic.users.security.GeneratorJWT;

@Service
public class UserService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IUserRepository repository;

	@Autowired
	private GeneratorJWT generatorJWT;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserDataResponseLogin getUserById(UUID id) throws UserNotFoundException {
		Optional<User> userOpt = this.repository.findById(id);
		User user = null;
		if (userOpt.isPresent()) {
			user = userOpt.get();
			user.setLastLogin(LocalDateTime.now());
		} else {
			throw new UserNotFoundException();
		}
		return this.modelMapper.map(user, UserDataResponseLogin.class);
	}

	public User create(User user) {
		User savedUser = this.modelMapper.map(user, User.class);
		savedUser.setCreated(LocalDateTime.now());
		savedUser.setIsActive(true);
		savedUser.setLastLogin(null);
		savedUser.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			savedUser = this.repository.save(savedUser);
		} catch (DataIntegrityViolationException e) {
			throw new UserEmailAlreadyExistiException();
		}
		savedUser.setToken(this.generateTokenById(savedUser.getId()));
		return savedUser;

	}

	// Genera el token mediante el UUID y actualiza el token por ID.
	public String generateTokenById(UUID id) {
		String token = this.generatorJWT.getJWTToken(id);
		this.repository.updateTokenById(token, id);
		return token;
	}

}
