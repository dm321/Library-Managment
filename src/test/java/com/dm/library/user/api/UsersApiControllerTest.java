package com.dm.library.user.api;

import com.dm.library.user.common.AbstractUserTest;
import com.dm.library.user.exception.RestResponseEntityExceptionHandler;
import com.dm.library.user.exception.UserDoesNotExistException;
import com.dm.library.user.model.dto.UserCreateCommand;
import com.dm.library.user.model.dto.UserDto;
import com.dm.library.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = UsersApiController.class )
@Import({UsersApiControllerTest.EmployeeServiceImplTestContextConfiguration.class, UsersApiController.class,
        RestResponseEntityExceptionHandler.class})
@TestPropertySource("/application-test.properties")
class UsersApiControllerTest extends AbstractUserTest {
    
    private static final String INVALID_PERSONID = "test1";
    private static final String INVALID_EMAIL = "test.domain.com";
    @MockBean
    UserService userService;
    @Captor
    ArgumentCaptor<UserDto> userDtoCaptor;
    @Autowired
    MockMvc mockMvc;
    @Value("${base.path}")
    private String basePath;
    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void resetMocks() {
        Mockito.reset(userService);
    }


    @Test
    public void testPostUser_Positive() throws Exception {
        var userCreateCommand = createUserCreateCommand();
        var createUserDto = getInstanceOfUserDto();
        Mockito.when(userService.createUser(Mockito.eq(userCreateCommand)))
                .thenReturn(createUserDto);

        mockMvc.perform(post(basePath + "/users/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateCommand)))
                .andExpect(jsonPath("$.personId", Matchers.is(PERSONID)))
                .andExpect(jsonPath("$.firstName", Matchers.is(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", Matchers.is(LASTNAME)))
                .andExpect(jsonPath("$.email", Matchers.is(EMAIL)));

        Mockito.verify(userService, Mockito.times(1))
                .createUser(Mockito.eq(createUserCreateCommand()));

    }

    @Test
    public void testPostUser_InvalidEmail() throws Exception {
        var userCreateCommand = createUserCreateCommand();
        userCreateCommand.setEmail(INVALID_EMAIL);
        mockMvc.perform(post(basePath + "/users/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateCommand)))
                .andExpect(status().isBadRequest());


    }

    @Test
    public void testUsersPersonIdGet_Positive() throws Exception {
        Mockito.when(userService.findUser(Mockito.eq(PERSONID)))
                .thenReturn(getInstanceOfUserDto());

        mockMvc.perform(get(basePath + "/users/{personId}", PERSONID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.personId", Matchers.is(PERSONID)))
                .andExpect(jsonPath("$.firstName", Matchers.is(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", Matchers.is(LASTNAME)))
                .andExpect(jsonPath("$.email", Matchers.is(EMAIL)));
    }

    @Test
    public void testGetUser_UserNotExists() throws Exception {
        Mockito.when(userService.findUser(Mockito.eq(PERSONID)))
                .thenThrow(new UserDoesNotExistException("User does not exist"));

        mockMvc.perform(get(basePath + "/users/{personId}", PERSONID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        Mockito.verify(userService, Mockito.times(1))
                .findUser(PERSONID);
    }

    @Test
    public void testGetUser_PersonIdInvalid() throws Exception {

        mockMvc.perform(get(basePath + "/users/{personId}", INVALID_PERSONID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

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

        @Bean
        LocalValidatorFactoryBean localValidatorFactoryBean() {
            return new LocalValidatorFactoryBean();
        }

    }

}
