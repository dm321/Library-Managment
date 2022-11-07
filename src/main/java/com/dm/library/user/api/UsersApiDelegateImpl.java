package com.dm.library.user.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dm.library.user.model.dto.UserCreateCommand;
import com.dm.library.user.model.dto.UserDto;
import com.dm.library.user.service.UserService;

@Service
public class UsersApiDelegateImpl implements UsersApiDelegate{
	
	@Autowired
	private UserService userService;
    
    @Override
    public ResponseEntity<List<UserDto>> usersGet(Integer limit,
        String markerUser) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<UserDto> usersPersonIdDelete(String personId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    
    @Override
    public ResponseEntity<UserDto> usersPersonIdGet(String personId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<UserDto> usersPersonIdPut(String personId,
        UserDto userDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<UserDto> usersPost(UserCreateCommand userCreateCommand) {
    	return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
    
    

}
