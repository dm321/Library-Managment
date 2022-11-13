package com.dm.library.user.service;

import com.dm.library.user.exception.UserDoesNotExistException;
import com.dm.library.user.model.dto.UserCreateCommand;
import com.dm.library.user.model.dto.UserDto;
import com.dm.library.user.model.dto.UserUpdateCommand;

public interface UserService {

	/**
	 *
	 * @param userCreateCommand
	 * @return
	 */
	UserDto createUser(UserCreateCommand userCreateCommand);

	/**
	 *
	 * @param personId
	 * @param userCreateCommand
	 * @return
	 * @throws UserDoesNotExistException
	 */
	UserDto updateUser(String personId, UserUpdateCommand userCreateCommand) throws UserDoesNotExistException;

	/**
	 *
	 * @param personId
	 * @return
	 * @throws UserDoesNotExistException
	 */
	UserDto deleteUser(String personId)throws UserDoesNotExistException;

	/**
	 *
	 * @param personId
	 * @return
	 * @throws UserDoesNotExistException
	 */
	UserDto findUser(String personId) throws UserDoesNotExistException;

}
