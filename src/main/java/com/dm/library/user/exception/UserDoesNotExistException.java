package com.dm.library.user.exception;

public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException(String msg)
    {
        super(msg);
    }

    public static UserDoesNotExistException createException(String argumentName, String argumentValue)
    {
        return new UserDoesNotExistException(String.format("User with %s  %s does not exist", argumentName, argumentValue));
    }
}
