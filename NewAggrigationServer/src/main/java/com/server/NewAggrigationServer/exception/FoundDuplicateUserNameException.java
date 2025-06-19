package com.server.NewAggrigationServer.exception;

public class FoundDuplicateUserNameException extends RuntimeException{
    public FoundDuplicateUserNameException(String errorMessage){
        super(errorMessage);
    }
}
