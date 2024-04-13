package SortingAlgorithms;

import Exeptions.InvalidArrayLengthException;

public class BubbleSort implements Strategy
{
    @Override
    public void sort(int[] array) throws InvalidArrayLengthException
    {
        if (array.length < 3)
        {
            throw new InvalidArrayLengthException("Array must have at least 3 elements.");
        }
        int i, j, temp;
        int n = array.length;
        boolean swapped;
        for (i = 0; i < n - 1; i++)
        {
            swapped = false;
            for (j = 0; j < n - i - 1; j++)
            {
                if (array[j] > array[j + 1])
                {

                    //Swap arr[j] and arr[j+1]
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            //If no two elements were
            // swapped by inner loop, then break
            if (!swapped)
                break;
        }
    }
}
