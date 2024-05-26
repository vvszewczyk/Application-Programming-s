package org.example.SpiralMatrix;

import org.example.Exeptions.InvalidMatrixDimensionsException;

public class SpiralMatrix
{
    public static void printSpiral(int[][] matrix) throws InvalidMatrixDimensionsException
    {
        if (matrix.length == 0)
        {
            throw new InvalidMatrixDimensionsException("Invalid elements of matrix");
        }
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (true)
        {
            for (int i = top; i <= bottom; i++)
            {
                System.out.print(matrix[i][left] + " ");
            }
            left++;
            if (left > right)
                break;

            // Od lewej do prawej
            for (int i = left; i <= right; i++)
            {
                System.out.print(matrix[bottom][i] + " ");
            }
            bottom--;
            if (top > bottom)
                break;

            for (int i = bottom; i >= top; i--)
            {
                System.out.print(matrix[i][right] + " ");
            }
            right--;
            if (left > right)
                break;

            for (int i = right; i >= left; i--)
            {
                System.out.print(matrix[top][i] + " ");
            }
            top++;
            if (top > bottom)
                break;
        }
    }

    /*public static void runExercise5() throws InvalidMatrixDimensionsException
    {
        int[][] matrix = {
                { 1, 2, 3, 4, 5},
                { 6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        System.out.print("\n");
        printSpiral(matrix);
    }*/
}
