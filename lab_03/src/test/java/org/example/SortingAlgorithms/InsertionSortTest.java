package org.example.SortingAlgorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InsertionSortTest
{
    @Test
    void testInsertionSortWithRandomArray() throws org.example.Exeptions.InvalidArrayLengthException
    {
        InsertionSort insertionSort = new InsertionSort();
        int[] array = {5, 3, 8, 4, 2};
        int[] expectedArray = {2, 3, 4, 5, 8};
        insertionSort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testInsertionSortWithAscendingArray() throws org.example.Exeptions.InvalidArrayLengthException
    {
        InsertionSort insertionSort = new InsertionSort();
        int[] array = {1, 2, 3, 4, 5};
        int[] expectedArray = {1, 2, 3, 4, 5};
        insertionSort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testInsertionSortWithDescendingArray() throws org.example.Exeptions.InvalidArrayLengthException
    {
        InsertionSort insertionSort = new InsertionSort();
        int[] array = {5, 4, 3, 2, 1};
        int[] expectedArray = {1, 2, 3, 4, 5};
        insertionSort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testInsertionSortWithInvalidArrayLength()
    {
        InsertionSort insertionSort = new InsertionSort();
        int[] array = {1};
        assertThrows(org.example.Exeptions.InvalidArrayLengthException.class, () -> insertionSort.sort(array));
    }
}