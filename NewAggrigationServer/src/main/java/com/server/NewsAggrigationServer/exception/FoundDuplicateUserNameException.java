package com.server.NewsAggrigationServer.exception;

public class FoundDuplicateUserNameException extends RuntimeException{
    public FoundDuplicateUserNameException(String errorMessage){
        super(errorMessage);
    }
}
