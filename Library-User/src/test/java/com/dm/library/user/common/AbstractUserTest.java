package com.dm.library.user.common;

import com.dm.library.user.model.User;
import com.dm.library.user.model.dto.UserDto;

import java.util.UUID;

public abstract class AbstractUserTest {
    protected static final String EMAIL = "example@test.com";
    protected static final String FIRST_NAME = "testFirstName";
    protected static final String LASTNAME = "testLastName";
    protected static final String EMAIL_UPDATE = "example@testUpdate.com";
    protected static final String FIRST_NAME_UPDATE = "testFirstNameUpdate";
    protected static final String LASTNAME_UPDATE = "testLastNameUpdate";
    protected static final String PERSONID = UUID.randomUUID()
            .toString();

    protected User getInstanceOfUser(boolean withPersonId) {
        var user = new User();
        user.setEmail(EMAIL);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LASTNAME);
        user.setPersonId(withPersonId? UUID.randomUUID() : null);
        return user;
    }

    protected UserDto getInstanceOfUserDto() {
        var userDto = new UserDto();
        userDto.setEmail(EMAIL);
        userDto.setFirstName(FIRST_NAME);
        userDto.setLastName(LASTNAME);
        userDto.setPersonId(PERSONID.toString());
        return userDto;
    }
}
