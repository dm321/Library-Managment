package com.dm.library.user.service;

import com.dm.library.user.model.dto.UserCreateCommand;
import com.dm.library.user.model.dto.UserDto;

public interface UserService {

	UserDto createUser(UserCreateCommand userCreateCommand);

	UserDto updateUser(UserCreateCommand userCreateCommand);

	void deleteUser(String personId);

	UserDto findUser(String personId);

}
