package org.example.Exeptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidStringLengthTest
{
    @Test
    void testExceptionMessage()
    {
        Exception exception = assertThrows(InvalidStringLength.class, () ->
        {
            throw new InvalidStringLength("Custom error message");
        });
        assertEquals("Custom error message", exception.getMessage());
    }
}
