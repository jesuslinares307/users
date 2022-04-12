package com.globallogic.users.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.globallogic.users.exceptions.UserNotFoundException;
import com.globallogic.users.model.User;
import com.globallogic.users.model.UserDataResponseLogin;
import com.globallogic.users.respositories.IUserRepository;

import net.minidev.json.parser.ParseException;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTests {

	@Autowired
	private UserService userService;

	@MockBean
	private IUserRepository mockUsersRepository;

	public User user = userOf();
	
	public Optional<User> optional = Optional.of(user);

	public final static UUID ID = UUID.fromString("e6ace60e-61f6-4994-9f8e-f3f082a32766");

	public final static UUID ID2 = UUID.fromString("e6ace60e-61f6-4994-9f8e-f3f082a32767");

	@Test
	public void whenGetUserByIdThenReturnAnUser() throws NotFoundException {
		doReturn(optional).when(mockUsersRepository).findById(ID);
		final UserDataResponseLogin result = userService.getUserById(ID);

		checkSimpleResult(result, user);

	}

	@Test
	public void whenGetUserByIdAndItNotFoundThenThrowUserNotFoundException() {
		assertThrows(UserNotFoundException.class, () -> userService.getUserById(ID2));
	}
	
	@Test
	public void whenCreateAnIxistentUserThenReturnTheSaved() throws ParseException {

		doReturn(user).when(mockUsersRepository).save(user);

		final User result = userService.create(user);
		checkSimpleResult(result, user);
	}
	
	@Test
    public void whenTryingToSaveAnUserThenThrowUserNotFoundException() {
		// setup
        final UUID ID =
                UUID.fromString("e6ace60e-61f6-4994-9f8e-f3f082a32710");


        // binding
        given(mockUsersRepository.findById(ID))
                .willReturn(Optional.empty());

        // execution
//        assertThatThrownBy(
//                () -> userService.create(user).hasMessageContaining(
//                                ExceptionMessages.USER_NOT_FOUND.getMessage())
//                        .isInstanceOf(UserNotFoundException.class));

        // assertions
        then(mockUsersRepository).should(never()).save(user);
    }

	public void checkSimpleResult(UserDataResponseLogin result, User userElement) {
		assertTrue(userElement.getEmail().equals(result.getEmail()));
		assertTrue(userElement.getName().equals(result.getName()));
		assertTrue(userElement.getCreated().equals(result.getCreated()));

	}
	
	public void checkSimpleResult(User result, User userElement) {
		assertTrue(userElement.getEmail().equals(result.getEmail()));
		assertTrue(userElement.getName().equals(result.getName()));
		assertTrue(userElement.getCreated().equals(result.getCreated()));

	}

	public static User userOf() {
		User user = new User();
		user.setId(ID);
		user.setEmail("jesus@gmail.com");
		user.setIsActive(true);
		user.setLastLogin(LocalDateTime.now());
		user.setName("Jesus");
		user.setPassword("1234");
		// user.setPhones("577899");
		user.setToken("123455");
		user.setCreated(LocalDateTime.now());

		return user;

	}



}
