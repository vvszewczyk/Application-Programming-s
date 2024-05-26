package org.example.SortingAlgorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest
{
    @Test
    void testMergeSortWithRandomArray() throws org.example.Exeptions.InvalidArrayLengthException
    {
        MergeSort mergeSort = new MergeSort();
        int[] array = {5, 3, 8, 4, 2};
        int[] expectedArray = {2, 3, 4, 5, 8};
        mergeSort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testMergeSortWithAscendingArray() throws org.example.Exeptions.InvalidArrayLengthException
    {
        MergeSort mergeSort = new MergeSort();
        int[] array = {1, 2, 3, 4, 5};
        int[] expectedArray = {1, 2, 3, 4, 5};
        mergeSort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testMergeSortWithDescendingArray() throws org.example.Exeptions.InvalidArrayLengthException
    {
        MergeSort mergeSort = new MergeSort();
        int[] array = {5, 4, 3, 2, 1};
        int[] expectedArray = {1, 2, 3, 4, 5};
        mergeSort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

}