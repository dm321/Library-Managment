package com.dm.library.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dm.library.user.mapper.UserMapper;
import com.dm.library.user.model.User;
import com.dm.library.user.model.dto.UserCreateCommand;
import com.dm.library.user.model.dto.UserDto;
import com.dm.library.user.persistence.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDto createUser(UserCreateCommand userCreateCommand) {
		User user = userMapper.userCreateCommandToUser(userCreateCommand);
		userRepository.save(user);
		return userMapper.UserToUserDto(user);
	}

	@Override
	public UserDto updateUser(UserCreateCommand userCreateCommand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(String personId) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserDto findUser(String personId) {
		// TODO Auto-generated method stub
		return null;
	}

}
