package org.example.SortingAlgorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest
{
    @Test
    void testQuickSortWithRandomArray() throws org.example.Exeptions.InvalidArrayLengthException
    {
        QuickSort quickSort = new QuickSort();
        int[] array = {5, 3, 8, 4, 2};
        int[] expectedArray = {2, 3, 4, 5, 8};
        quickSort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testQuickSortWithAscendingArray() throws org.example.Exeptions.InvalidArrayLengthException
    {
        QuickSort quickSort = new QuickSort();
        int[] array = {1, 2, 3, 4, 5};
        int[] expectedArray = {1, 2, 3, 4, 5};
        quickSort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testQuickSortWithDescendingArray() throws org.example.Exeptions.InvalidArrayLengthException
    {
        QuickSort quickSort = new QuickSort();
        int[] array = {5, 4, 3, 2, 1};
        int[] expectedArray = {1, 2, 3, 4, 5};
        quickSort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testQuickSortWithInvalidArrayLength()
    {
        QuickSort quickSort = new QuickSort();
        int[] array = {1};
        assertThrows(org.example.Exeptions.InvalidArrayLengthException.class, () -> quickSort.sort(array));
    }
}