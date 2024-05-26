package org.example.SpiralMatrix;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class SpiralMatrixTest
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams()
    {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams()
    {
        System.setOut(originalOut);
    }


    @Test
    void testPrintSpiralWithEmptyMatrix()
    {
        int[][] matrix = {};
        Exception exception = assertThrows(org.example.Exeptions.InvalidMatrixDimensionsException.class, () -> SpiralMatrix.printSpiral(matrix));
        assertEquals("Invalid elements of matrix", exception.getMessage());
    }

    @Test
    void testPrintSpiralWithSingleElementMatrix() throws org.example.Exeptions.InvalidMatrixDimensionsException
    {
        int[][] matrix = {{1}};
        SpiralMatrix.printSpiral(matrix);
        assertEquals("1 ", outContent.toString());
    }

    @Test
    void testPrintSpiralWith3x3Matrix() throws org.example.Exeptions.InvalidMatrixDimensionsException
    {
        int[][] matrix = {{1, 2, 3}, {8, 9, 4}, {7, 6, 5}};
        SpiralMatrix.printSpiral(matrix);
        assertEquals("1 8 7 6 5 4 3 2 9 ", outContent.toString());
    }

    @Test
    void testPrintSpiralWith2x2Matrix() throws org.example.Exeptions.InvalidMatrixDimensionsException
    {
        int[][] matrix = {{1, 2}, {4, 3}};
        SpiralMatrix.printSpiral(matrix);
        assertEquals("1 4 3 2 ", outContent.toString());
    }

    @Test
    void testPrintSpiralWith2x3Matrix() throws org.example.Exeptions.InvalidMatrixDimensionsException
    {
        int[][] matrix =
                {{1, 2, 3}, {6, 5, 4}};
        SpiralMatrix.printSpiral(matrix);
        assertEquals("1 6 5 4 3 2 ", outContent.toString());
    }

    @Test
    void testPrintSpiralWithLargerMatrix() throws org.example.Exeptions.InvalidMatrixDimensionsException
    {
        int[][] matrix =
                {
                {1, 2, 3, 4},
                {12, 13, 14, 5},
                {11, 16, 15, 6},
                {10, 9, 8, 7}
        };
        SpiralMatrix.printSpiral(matrix);
        assertEquals("1 12 11 10 9 8 7 6 5 4 3 2 13 16 15 14 ", outContent.toString());
    }

}