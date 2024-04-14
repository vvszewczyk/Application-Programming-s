package org.example.SortingAlgorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SelectionSortTest
{
    @Test
    void testSelectionSortWithRandomArray() throws org.example.Exeptions.InvalidArrayLengthException
    {
        SelectionSort selectionSort = new SelectionSort();
        int[] array = {5, 3, 8, 4, 2};
        int[] expectedArray = {2, 3, 4, 5, 8};
        selectionSort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testSelectionSortWithAscendingArray() throws org.example.Exeptions.InvalidArrayLengthException
    {
        SelectionSort selectionSort = new SelectionSort();
        int[] array = {1, 2, 3, 4, 5};
        int[] expectedArray = {1, 2, 3, 4, 5};
        selectionSort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testSelectionSortWithDescendingArray() throws org.example.Exeptions.InvalidArrayLengthException
    {
        SelectionSort selectionSort = new SelectionSort();
        int[] array = {5, 4, 3, 2, 1};
        int[] expectedArray = {1, 2, 3, 4, 5};
        selectionSort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testQuickSortWithInvalidArrayLength()
    {
        SelectionSort selectionSort = new SelectionSort();
        int[] array = {1};
        assertThrows(org.example.Exeptions.InvalidArrayLengthException.class, () -> selectionSort.sort(array));
    }
}