package com.dm.library.user.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;

import com.dm.library.user.model.User;
import com.dm.library.user.model.dto.UserCreateCommand;
import com.dm.library.user.model.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDto UserToUserDto(User user);

	User userDtoToUser(UserDto userDto);
	
	User userCreateCommandToUser(UserCreateCommand userCreateCommand);
	
	default String map(UUID value) {
		return value.toString();
	}
	
	default UUID map(String value) {
		return UUID.fromString(value);
	}

}
