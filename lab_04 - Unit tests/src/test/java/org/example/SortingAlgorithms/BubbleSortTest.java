package org.example.SortingAlgorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BubbleSortTest
{
    @Test
    void testBubbleSortWithRandomArray() throws org.example.Exeptions.InvalidArrayLengthException
    {
        BubbleSort bubbleSort = new BubbleSort();
        int[] array = {5, 3, 8, 4, 2};
        int[] expectedArray = {2, 3, 4, 5, 8};
        bubbleSort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testBubbleSortWithAscendingArray() throws org.example.Exeptions.InvalidArrayLengthException
    {
        BubbleSort bubbleSort = new BubbleSort();
        int[] array = {1, 2, 3, 4, 5};
        int[] expectedArray = {1, 2, 3, 4, 5};
        bubbleSort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testBubbleSortWithDescendingArray() throws org.example.Exeptions.InvalidArrayLengthException
    {
        BubbleSort bubbleSort = new BubbleSort();
        int[] array = {5, 4, 3, 2, 1};
        int[] expectedArray = {1, 2, 3, 4, 5};
        bubbleSort.sort(array);
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void testBubbleSortWithInvalidArrayLength()
    {
        BubbleSort bubbleSort = new BubbleSort();
        int[] array = {1};
        assertThrows(org.example.Exeptions.InvalidArrayLengthException.class, () -> bubbleSort.sort(array));
    }
}