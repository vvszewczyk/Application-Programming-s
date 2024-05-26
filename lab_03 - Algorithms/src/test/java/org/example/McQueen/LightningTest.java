package org.example.McQueen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LightningTest
{

    @Test
    void testConvertNormalCase() throws org.example.Exeptions.InvalidStringLength
    {
        String input = "PAYPALISHIRING";
        int numRows = 3;
        String expected = "PAHNAPLSIIGYIR";
        String result = Lightning.convert(input, numRows);
        assertEquals(expected, result, "Wrong conversion");
    }

    @Test
    void testConvertWithOneRow()
    {
        String input = "ABC";
        int numRows = 1;
        assertThrows(org.example.Exeptions.InvalidStringLength.class, () ->
        {
            Lightning.convert(input, numRows);
        }, "Powinien zostać zgłoszony wyjątek InvalidStringLength");
    }


    @Test
    void testConvertWithEmptyString()
    {
        String input = "";
        int numRows = 3;
        Exception exception = assertThrows(org.example.Exeptions.InvalidStringLength.class, () ->
        {
            Lightning.convert(input, numRows);
        });
        assertEquals("String should have at least 1 character", exception.getMessage());
    }

    @Test
    void testConvertWithLargeNumRows()
    {
        String input = "A";
        int numRows = Integer.MAX_VALUE;
        assertThrows(org.example.Exeptions.InvalidStringLength.class, () ->
        {
            Lightning.convert(input, numRows);
        });
    }

    @Test
    void testInputImmutability() throws org.example.Exeptions.InvalidStringLength
    {
        String originalInput = "ABCD";
        String input = new String(originalInput);
        int numRows = 2;
        Lightning.convert(input, numRows);
        assertEquals(originalInput, input, "Original input should not be changed after conversion.");
    }
}