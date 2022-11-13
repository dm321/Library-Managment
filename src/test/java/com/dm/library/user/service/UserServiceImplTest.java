package com.dm.library.user.service;

import com.dm.library.user.common.AbstractUserTest;
import com.dm.library.user.exception.UserDoesNotExistException;
import com.dm.library.user.mapper.UserMapper;
import com.dm.library.user.mapper.UserMapperImpl;
import com.dm.library.user.model.User;
import com.dm.library.user.model.dto.UserCreateCommand;
import com.dm.library.user.model.dto.UserDto;
import com.dm.library.user.model.dto.UserUpdateCommand;
import com.dm.library.user.persistence.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest extends AbstractUserTest {

    @Mock
    UserRepository userRepository;
    UserMapper userMapper = new UserMapperImpl();
    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService.setUserMapper(userMapper);
    }

    @Test
    void createUserTest_Positive() {
        var userCreateCommand = getInstanceOfUserCreateCommand();
        var user = getInstanceOfUser(true);
        var userDto = getInstanceOfUserDto();

        when(userRepository.save(any(User.class))).thenReturn(user);
        UserDto createdUserDto = userService.createUser(userCreateCommand);

        assertThat(createdUserDto.getEmail()).isEqualTo(EMAIL);
        assertThat(createdUserDto.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(createdUserDto.getLastName()).isEqualTo(LASTNAME);
    }

    @Test
    void updateUserTest_Positive() {
        var userCreateCommand = getInstanceOfUserCreateCommand();
        var user = getInstanceOfUser(true);
        var userUpdateCommand = getInstanceOfUserUpdateCommand();

        when(userRepository.findByPersonId(UUID.fromString(PERSONID))).thenReturn(user);
        UserDto updatedUser = userService.updateUser(PERSONID, userUpdateCommand);
        assertThat(equalsByAttributes(List.of("email", "lastName", "firstName"), updatedUser, user)).isTrue();
    }

    @Test
    void updateUserTest_Negativ_User_Not_Exists() {
        var userUpdateCommand = getInstanceOfUserUpdateCommand();

        when(userRepository.findByPersonId(UUID.fromString(PERSONID))).thenReturn(null);

        assertThrows(UserDoesNotExistException.class, () -> {
            userService.updateUser(PERSONID, userUpdateCommand);
        });
    }

    @Test
    void deleteUserTest_Positive() {
        var user = getInstanceOfUser(true);
        when(userRepository.deleteByPersonId(UUID.fromString(PERSONID))).thenReturn(List.of(user));
        UserDto deletedUser = userService.deleteUser(PERSONID);

        assertThat(equalsByAttributes(List.of("email", "firstName", "lastName"), deletedUser, user)).isTrue();
    }

    @Test
    void deleteUsertTest_Negative_User_Not_Exists() {
        when(userRepository.deleteByPersonId(UUID.fromString(PERSONID))).thenReturn(Arrays.asList());

        assertThrows(UserDoesNotExistException.class, () -> {
            userService.deleteUser(PERSONID);
        });
    }

    @Test
    void findUserTest_Positive() {
        User user = getInstanceOfUser(true);
        when(userRepository.findByPersonId(UUID.fromString(PERSONID))).thenReturn(user);

        UserDto foundUserDto = userService.findUser(PERSONID);
        assertThat(foundUserDto).isNotNull();
    }

    @Test
    void findUserTest_Negative_User_Not_Exists() {
        when(userRepository.findByPersonId(UUID.fromString(PERSONID))).thenReturn(null);
        assertThrows(UserDoesNotExistException.class, () -> {
            userService.findUser(PERSONID);
        });
    }


    private UserCreateCommand getInstanceOfUserCreateCommand() {
        var userCreateCommand = new UserCreateCommand();
        userCreateCommand.setEmail(EMAIL);
        userCreateCommand.setFirstName(FIRST_NAME);
        userCreateCommand.setLastName(LASTNAME);
        return userCreateCommand;
    }


    private UserUpdateCommand getInstanceOfUserUpdateCommand() {
        var userUpdateCommand = new UserUpdateCommand();
        userUpdateCommand.setEmail(EMAIL_UPDATE);
        userUpdateCommand.setFirstName(FIRST_NAME_UPDATE);
        userUpdateCommand.setLastName(LASTNAME_UPDATE);
        return userUpdateCommand;
    }

    private boolean equalsByAttributes(List<String> attributeNames, Object o1, Object o2) {
        for (String attributeName : attributeNames) {
            try {
                Field field1 = o1.getClass()
                        .getDeclaredField(attributeName);
                field1.setAccessible(true);
                Field field2 = o2.getClass()
                        .getDeclaredField(attributeName);
                field2.setAccessible(true);
                boolean fieldsEquals = field1.get(o1)
                        .equals(field2.get(o2));
                if (!fieldsEquals) {
                    return false;
                }
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        return true;

    }


}
