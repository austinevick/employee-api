package com.vickky.employee.exception;

public class NoSuchEmployException extends  RuntimeException{
    private String message;

    public NoSuchEmployException() {}

    public NoSuchEmployException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
