package com.greatnex.semicolon_task.logic.program;

public class ProgramAlreadyExistException extends Exception{
    public ProgramAlreadyExistException(String message) {
        super(message);
    }
}
