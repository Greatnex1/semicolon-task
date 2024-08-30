package com.greatnex.semicolon_task.exception;

public class ProgramNotFoundException extends  RuntimeException{

    public ProgramNotFoundException(String message){
        super(message);
    }
}
