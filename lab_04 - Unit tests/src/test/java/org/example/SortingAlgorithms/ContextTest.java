package org.example.SortingAlgorithms;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContextTest
{
    @Test
    void testQuickSortExecuteStrategy() throws org.example.Exeptions.InvalidArrayLengthException
    {
        System.out.println("QuickSort:");
        int[] array = {5, 3, 8, 4, 2};
        Strategy quickSort = new QuickSort();
        Context context = new Context(quickSort);
        long startTime = System.nanoTime();
        context.executeStrategy(array);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        assertTrue(executionTime > 0, "Time bigger than 0");
        assertArrayIsSorted(array);
    }

    @Test
    void testBubbleSortExecuteStrategy() throws org.example.Exeptions.InvalidArrayLengthException
    {
        System.out.println("BubbleSort:");
        int[] array = {5, 3, 8, 4, 2};
        Strategy bubbleSort = new BubbleSort();
        Context context = new Context(bubbleSort);
        long startTime = System.nanoTime();
        context.executeStrategy(array);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        assertTrue(executionTime > 0, "Time bigger than 0");
        assertArrayIsSorted(array);
    }

    @Test
    void testInsertionSortExecuteStrategy() throws org.example.Exeptions.InvalidArrayLengthException
    {
        System.out.println("InsertionSort:");
        int[] array = {5, 3, 8, 4, 2};
        Strategy insertionSort = new InsertionSort();
        Context context = new Context(insertionSort);
        long startTime = System.nanoTime();
        context.executeStrategy(array);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        assertTrue(executionTime > 0, "Time bigger than 0");
        assertArrayIsSorted(array);
    }

    @Test
    void testMergeSortExecuteStrategy() throws org.example.Exeptions.InvalidArrayLengthException
    {
        System.out.println("MergeSort:");
        int[] array = {5, 3, 8, 4, 2};
        Strategy mergeSort = new MergeSort();
        Context context = new Context(mergeSort);
        long startTime = System.nanoTime();
        context.executeStrategy(array);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        assertTrue(executionTime > 0, "Time bigger than 0");
        assertArrayIsSorted(array);
    }

    @Test
    void testSelectionSortExecuteStrategy() throws org.example.Exeptions.InvalidArrayLengthException
    {
        System.out.println("SelectionSort:");
        int[] array = {5, 3, 8, 4, 2};
        Strategy selectionSort = new SelectionSort();
        Context context = new Context(selectionSort);
        long startTime = System.nanoTime();
        context.executeStrategy(array);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        assertTrue(executionTime > 0, "Time bigger than 0");
        assertArrayIsSorted(array);
    }

    private void assertArrayIsSorted(int[] array)
    {
        for (int i = 0; i < array.length - 1; i++)
        {
            assertTrue(array[i] <= array[i + 1], "Array should be sorted");
        }
    }
}