package org.example.McQueen;
import org.example.Exeptions.InvalidStringLength;



public class Lightning
{
    public static String convert(String s, int numRows) throws InvalidStringLength
    {
        if (numRows == 1 || numRows >= s.length())
        {
            throw new InvalidStringLength("String should have at least 1 character");
        }

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++)
        {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray())
        {
            rows[currentRow].append(c);
            if (currentRow == 0 || currentRow == numRows - 1)
            {
                goingDown = !goingDown;
            }
            currentRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows)
        {
            result.append(row);
        }

        return result.toString();
    }

    /*public static void runExercise2() throws InvalidStringLength
    {
        String example1, example2;
        example1 = convert("PAYPALISHIRING", 3);
        example2 = convert("PAYPALISHIRING", 4);
        System.out.println(example1);
        System.out.println(example2);
    }*/

}
