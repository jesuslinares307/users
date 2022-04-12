package com.globallogic.users.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.globallogic.users.dto.UserDataResponseLogin;
import com.globallogic.users.exceptions.UserEmailAlreadyExistsException;
import com.globallogic.users.exceptions.UserNotFoundException;
import com.globallogic.users.model.Phone;
import com.globallogic.users.model.User;
import com.globallogic.users.respositories.UserRepository;
import com.globallogic.users.security.GeneratorJWT;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
	@Mock
	private UserRepository userRepository;
	@Mock
	private ModelMapper modelMapper;
	@Mock
	private GeneratorJWT generatorJWT;
	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserService underTest;

	public final static UUID USER_ID = UUID.fromString("e6ace60e-61f6-4994-9f8e-f3f082a32766");

	@Test
	void whenGetUserByIdThenReturnUserDataResponseLogin() {
		// given
		final User user = userOf(USER_ID);
		UserDataResponseLogin userDataResponseLogin = new UserDataResponseLogin();
		userDataResponseLogin.setId(USER_ID);

		given(userRepository.findById(USER_ID)).willReturn(Optional.of(user));
		given(modelMapper.map(user, UserDataResponseLogin.class)).willReturn(userDataResponseLogin);

		// when
		UserDataResponseLogin result = underTest.getUserById(USER_ID);

		// then
		assertTrue(result != null);
		assertThat(result.getId()).isEqualTo(USER_ID);
	}

	@Test
	void whenUserIsNotFoundThenThrowUserNotFoundException() {
		// given
		given(userRepository.findById(USER_ID)).willReturn(Optional.empty());

		// when/then
		assertThatThrownBy(() -> underTest.getUserById(USER_ID)).isInstanceOf(UserNotFoundException.class);
	}

	@Test
	void whenCreatingAUserReturnASuccessfulUser() {
		// given
		final String name = "jesus";
		final String email = "jesus@gmail.com";
		final List<Phone> phones = new ArrayList<>();
		Phone phone = new Phone();
		phone.setNumber(1234L);
		phone.setCityCode(123);
		phone.setCountryCode("arg");
		phones.add(phone);
		final LocalDateTime created = LocalDateTime.now();
		final boolean isActive = true;
		final LocalDateTime lastLogin = LocalDateTime.now();
		final String password = "P4ssword";
		final String token = "token";

		final User userRequest = userRequest(name, email, password, phones);
		final User savedUser = savedUser(USER_ID, name, email, phones, created, isActive, lastLogin, password);

		given(userRepository.findUserByEmail(userRequest.getEmail())).willReturn(Optional.empty());
		given(userRepository.save(userRequest)).willReturn(savedUser);
		given(generatorJWT.getJWTToken(USER_ID)).willReturn("token");
		given(userRepository.updateTokenById(token, USER_ID)).willReturn(1);
		given(passwordEncoder.encode(userRequest.getPassword())).willReturn(password);

		// when
		User userResult = underTest.create(userRequest);

		// then
		assertThat(userRequest.getName()).isEqualTo(name);
		assertThat(userRequest.getEmail()).isEqualTo(email);
		assertThat(userRequest.getPassword()).isEqualTo(password);
		assertThat(userRequest.getPhones().size()).isEqualTo(1);

		assertThat(userResult.getName()).isEqualTo(name);
		assertThat(userResult.getEmail()).isEqualTo(email);
		assertThat(userResult.getPassword()).isEqualTo(password);
		assertThat(userResult.getPhones().size()).isEqualTo(1);
		assertThat(userResult.getCreated()).isEqualTo(created);
		assertThat(userResult.getLastLogin()).isEqualTo(lastLogin);
		assertThat(userResult.isIsActive()).isEqualTo(isActive);
		assertThat(userResult.getToken()).isEqualTo(token);
	}

	@Test
	void whenTryingToCreateAUserThrowUserEmailAlreadyExistiException() {
		// given
		final String name = "jesus";
		final String email = "jesus@gmail.com";
		final List<Phone> phones = new ArrayList<>();
		Phone phone = new Phone();
		phone.setNumber(1234L);
		phone.setCityCode(123);
		phone.setCountryCode("arg");
		phones.add(phone);
		final String password = "P4ssword";

		final User userRequest = userRequest(name, email, password, phones);

		given(userRepository.findUserByEmail(userRequest.getEmail())).willReturn(Optional.of(new User()));

		// when/then
		assertThatThrownBy(() -> underTest.create(userRequest)).isInstanceOf(UserEmailAlreadyExistsException.class);
	}

	private User savedUser(UUID id, String name, String email, List<Phone> phones, LocalDateTime created,
			boolean isActive, LocalDateTime lastLogin, String password) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setEmail(email);
		user.setPhones(phones);
		user.setCreated(created);
		user.setIsActive(isActive);
		user.setLastLogin(lastLogin);
		user.setPassword(password);
		return user;
	}

	private User userRequest(String name, String email, String password, List<Phone> phones) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhones(phones);
		return user;
	}

	private User userOf(UUID id) {
		User user = new User();
		user.setId(id);
		return user;
	}

}
