package com.dm.library.user.service;

import com.dm.library.user.exception.UserDoesNotExistException;
import com.dm.library.user.mapper.UserMapper;
import com.dm.library.user.model.User;
import com.dm.library.user.model.dto.UserCreateCommand;
import com.dm.library.user.model.dto.UserDto;
import com.dm.library.user.model.dto.UserUpdateCommand;
import com.dm.library.user.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto createUser(UserCreateCommand userCreateCommand) {
        var user = userMapper.userCreateCommandToUser(userCreateCommand);
        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(String personId, UserUpdateCommand userCreateCommand) {

        var user = userRepository.findByPersonId(UUID.fromString(personId));
        if (user == null) {
            throw UserDoesNotExistException.createException("personId", personId);
        }
        updateUser(user, userCreateCommand);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto deleteUser(String personId) {
        return userRepository.deleteByPersonId(UUID.fromString(personId))
                .stream()
                .map(user -> userMapper.userToUserDto(user))
                .findFirst()
                .orElseThrow(() -> UserDoesNotExistException.createException("personId", personId));

    }

    @Override
    public UserDto findUser(String personId) {
        return Optional.ofNullable(userRepository.findByPersonId(UUID.fromString(personId)))
                .map(user -> userMapper.userToUserDto(user))
                .orElseThrow(() -> UserDoesNotExistException.createException("personId", personId));
    }

    private void updateUser(User user, UserUpdateCommand userUpdateCommand) {
        if (!user.getEmail()
                .equals(userUpdateCommand.getEmail())) {
            user.setEmail(userUpdateCommand.getEmail());
        }
        if (!user.getFirstName()
                .equals(userUpdateCommand.getFirstName())) {
            user.setFirstName(userUpdateCommand.getFirstName());
        }
        if (!user.getLastName()
                .equals(userUpdateCommand.getLastName())) {
            user.setLastName(userUpdateCommand.getLastName());
        }
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
