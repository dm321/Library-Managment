package com.dm.library.user.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dm.library.user.model.dto.UserCreateCommand;
import com.dm.library.user.model.dto.UserDto;

public class UsersApiDelegateImpl implements UsersApiDelegate{
    
    @Override
    public ResponseEntity<Void> usersGet(Integer limit,
        String markerUser) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<Void> usersPersonIdDelete(String personId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    
    @Override
    public ResponseEntity<UserDto> usersPersonIdGet(String personId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<Void> usersPersonIdPut(String personId,
        UserDto userDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<Void> usersPost(UserCreateCommand userCreateCommand) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
