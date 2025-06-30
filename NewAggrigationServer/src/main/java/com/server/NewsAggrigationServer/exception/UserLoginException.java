package com.server.NewsAggrigationServer.exception;

public class UserLoginException extends RuntimeException{
    public UserLoginException(String message){
        super(message);
    }
}
