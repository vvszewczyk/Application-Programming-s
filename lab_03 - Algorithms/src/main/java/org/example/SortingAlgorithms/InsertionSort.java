package org.example.SortingAlgorithms;

import org.example.Exeptions.InvalidArrayLengthException;

public class InsertionSort implements Strategy
{
    @Override
    public void sort(int[] array) throws InvalidArrayLengthException
    {
        if (array.length < 3)
        {
            throw new InvalidArrayLengthException("Array must have at least 3 elements.");
        }

        for (int i = 1; i < array.length; ++i)
        {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key)
            {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }
}
