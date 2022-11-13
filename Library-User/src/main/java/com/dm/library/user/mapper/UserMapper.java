package com.dm.library.user.mapper;

import com.dm.library.user.model.User;
import com.dm.library.user.model.dto.UserCreateCommand;
import com.dm.library.user.model.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDto userToUserDto(User user);

	User userDtoToUser(UserDto userDto);
	
	User userCreateCommandToUser(UserCreateCommand userCreateCommand);
	
	default String map(UUID personId) {
		return personId.toString();
	}
	
	default UUID map(String personId) {
		return UUID.fromString(personId);
	}

}
