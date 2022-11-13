package com.dm.library.user.api;

import com.dm.library.user.model.dto.UserCreateCommand;
import com.dm.library.user.model.dto.UserDto;
import com.dm.library.user.model.dto.UserUpdateCommand;
import com.dm.library.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersApiDelegateImpl implements UsersApiDelegate {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<List<UserDto>> usersGet(Integer limit,
                                                  String markerUser) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Override
    public ResponseEntity<UserDto> usersPersonIdDelete(String personId) {
        var user = userService.deleteUser(personId);
        return ResponseEntity.ok(user);

    }


    @Override
    public ResponseEntity<UserDto> usersPersonIdGet(String personId) {
        var user = userService.findUser(personId);
        return ResponseEntity.ok(user);

    }

    @Override
    public ResponseEntity<UserDto> usersPersonIdPut(String personId,
                                                    UserUpdateCommand userUpdateCommand) {
        var updatedUser = userService.updateUser(personId, userUpdateCommand);
        return ResponseEntity.ok(updatedUser);

    }

    @Override
    public ResponseEntity<UserDto> usersPost(UserCreateCommand userCreateCommand) {
        var createdUser = userService.createUser(userCreateCommand);
        return ResponseEntity.created(null).body(createdUser);

    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
