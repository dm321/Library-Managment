package com.dm.library.user.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.UUID;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.dm.library.user.model.dto.UserCreateCommand;
import com.dm.library.user.model.dto.UserDto;
import com.dm.library.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UsersApiController.class)
@Import({ UsersApiControllerTest.EmployeeServiceImplTestContextConfiguration.class, UsersApiController.class })
class UsersApiControllerTest {

	private static final String EMAIL = "test@unittest.com";
	private static final String FIRST_NAME = "testFirstName";
	private static final String LASTNAME = "testLastName";
	private static final String PERSON_ID = UUID.randomUUID().toString();
	private static final String BASE_PATH = "/library-managment/1.0.0";

	@MockBean
	UserService userService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testPostUser() throws Exception {
		Mockito.when(userService.createUser(Mockito.any(UserDto.class))).thenReturn(createUserDto());

		mockMvc.perform(post(BASE_PATH + "/users/").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(createUserCreateCommand())))
				.andExpect(jsonPath("$.personId", Matchers.is(PERSON_ID)))
				.andExpect(jsonPath("$.firstName", Matchers.is(FIRST_NAME)))
				.andExpect(jsonPath("$.lastName", Matchers.is(LASTNAME)))
				.andExpect(jsonPath("$.email", Matchers.is(EMAIL)));
	}

	private UserDto createUserDto() {
		var userDto = new UserDto();
		userDto.setEmail(EMAIL);
		userDto.setFirstName(FIRST_NAME);
		userDto.setLastName(LASTNAME);
		userDto.setPersonId(PERSON_ID);

		return userDto;
	}

	private UserCreateCommand createUserCreateCommand() {
		var userCreateCommand = new UserCreateCommand();
		userCreateCommand.setEmail(EMAIL);
		userCreateCommand.setFirstName(FIRST_NAME);
		userCreateCommand.setLastName(LASTNAME);
		return userCreateCommand;

	}

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public UsersApiDelegate usersApiDelegate() {
			return new UsersApiDelegateImpl();
		}
	}

}
