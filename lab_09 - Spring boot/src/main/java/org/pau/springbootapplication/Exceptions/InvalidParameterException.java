package org.pau.springbootapplication.Exceptions;

public class InvalidParameterException extends RuntimeException
{
    public InvalidParameterException(String message)
    {
        super(message);
    }
}