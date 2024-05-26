package org.example.Exeptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidMatrixDimensionsExceptionTest
{
    @Test
    void testExceptionMessage()
    {
        Exception exception = assertThrows(InvalidMatrixDimensionsException.class, () ->
        {
            throw new InvalidMatrixDimensionsException("Custom error message");
        });
        assertEquals("Custom error message", exception.getMessage());
    }
}