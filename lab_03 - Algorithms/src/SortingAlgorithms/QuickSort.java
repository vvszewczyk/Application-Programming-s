package SortingAlgorithms;

import Exeptions.InvalidArrayLengthException;

public class QuickSort implements Strategy
{
    @Override
    public void sort(int[] arr) throws InvalidArrayLengthException
    {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) throws InvalidArrayLengthException
    {
        if (arr.length < 3)
        {
            throw new InvalidArrayLengthException("Array must have at least 3 elements.");
        }
        if (low < high)
        {
            int pivotIndex = partition(arr, low, high);

            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high)
    {
        int mid = low + (high - low) / 2;
        int pivot = medianOfThree(arr[low], arr[mid], arr[high]);
        swap(arr, mid, high);

        int i = (low - 1);
        for (int j = low; j < high; j++)
        {
            if (arr[j] <= pivot)
            {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }


    private static int medianOfThree(int a, int b, int c)
    {
        if ((a > b) != (a > c))
        {
            return a;
        }
        else if ((b > a) != (b > c))
        {
            return b;
        }
        else
        {
            return c;
        }
    }

    private static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
