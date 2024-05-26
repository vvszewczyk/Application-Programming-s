package org.example.SortingAlgorithms;

import org.example.Exeptions.InvalidArrayLengthException;

public class SelectionSort implements Strategy
{

    @Override
    public void sort(int[] array) throws InvalidArrayLengthException
    {
        if (array.length < 3)
        {
            throw new InvalidArrayLengthException("Array must have at least 3 elements.");
        }
        int n = array.length;

        for (int i = 0; i < n-1; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (array[j] < array[min_idx])
                    min_idx = j;

            int temp = array[min_idx];
            array[min_idx] = array[i];
            array[i] = temp;
        }
    }
}
