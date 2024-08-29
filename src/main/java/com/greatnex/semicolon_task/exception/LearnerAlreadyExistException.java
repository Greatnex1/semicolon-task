package com.greatnex.semicolon_task.exception;

public class LearnerAlreadyExistException extends Exception{
    public LearnerAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public LearnerAlreadyExistException(String message) {
        super(message);
    }
}
