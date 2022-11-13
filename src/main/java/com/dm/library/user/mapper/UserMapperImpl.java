package com.dm.library.user.mapper;

import com.dm.library.user.model.User;
import com.dm.library.user.model.dto.UserCreateCommand;
import com.dm.library.user.model.dto.UserDto;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-07T20:50:27+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }
        UserDto userDto = new UserDto();

        userDto.setPersonId( map( user.getPersonId() ) );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setEmail( user.getEmail() );

        return userDto;
    }

    @Override
    public User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setPersonId( map( userDto.getPersonId() ) );
        user.setFirstName( userDto.getFirstName() );
        user.setLastName( userDto.getLastName() );
        user.setEmail( userDto.getEmail() );

        return user;
    }

    @Override
    public User userCreateCommandToUser(UserCreateCommand userCreateCommand) {
        if ( userCreateCommand == null ) {
            return null;
        }

        User user = new User();

        user.setFirstName( userCreateCommand.getFirstName() );
        user.setLastName( userCreateCommand.getLastName() );
        user.setEmail( userCreateCommand.getEmail() );

        return user;
    }
}
