package com.dm.library.user.service;

import com.dm.library.user.model.dto.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);

	UserDto updaateUser(UserDto userDto);

	void deleteUser(String personId);

	UserDto findUser(String personId);

}
