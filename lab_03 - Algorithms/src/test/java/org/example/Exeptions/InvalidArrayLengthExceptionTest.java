package org.example.Exeptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidArrayLengthExceptionTest
{
    @Test
    void testExceptionMessage()
    {
        Exception exception = assertThrows(InvalidArrayLengthException.class, () ->
        {
            throw new InvalidArrayLengthException("Custom error message");
        });
        assertEquals("Custom error message", exception.getMessage());
    }
}