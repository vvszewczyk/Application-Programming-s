package org.example.SortingAlgorithms;

import org.example.Exeptions.InvalidArrayLengthException;

public class MergeSort implements Strategy
{
    @Override
    public void sort(int[] array) throws InvalidArrayLengthException
    {
        if (array.length < 0)
        {
            throw new InvalidArrayLengthException("Array must have at least 1 element.");
        }
        if (array.length < 2)
        {
            return;
        }

        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        System.arraycopy(array, 0, left, 0, mid);
        if (array.length - mid >= 0) System.arraycopy(array, mid, right, 0, array.length - mid);

        sort(left);
        sort(right);

        merge(array, left, right);
    }

    private static void merge(int[] array, int[] left, int[] right)
    {
        int leftIndex = 0, rightIndex = 0, mergeIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length)
        {
            if (left[leftIndex] <= right[rightIndex])
            {
                array[mergeIndex++] = left[leftIndex++];
            }
            else
            {
                array[mergeIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length)
        {
            array[mergeIndex++] = left[leftIndex++];
        }

        while (rightIndex < right.length)
        {
            array[mergeIndex++] = right[rightIndex++];
        }
    }
}
