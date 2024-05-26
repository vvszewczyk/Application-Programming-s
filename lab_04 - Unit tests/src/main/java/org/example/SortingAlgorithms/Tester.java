package org.example.SortingAlgorithms;
import org.example.Exeptions.InvalidArrayLengthException;
import org.example.SortingAlgorithms.BubbleSort;
import org.example.SortingAlgorithms.Strategy;

import java.util.Random;

public class Tester
{
   /* public static void runExercise1() throws InvalidArrayLengthException
    {
        Tester test = new Tester();

        Strategy bubbleSort = (Strategy) new BubbleSort();
        test.runTests(bubbleSort, "Bubble Sort");

        Strategy selectionSort = (Strategy) new SelectionSort();
        test.runTests(selectionSort, "Selection Sort");

        Strategy quickSort = (Strategy) new QuickSort();
        test.runTests(quickSort, "Quick Sort");

        Strategy mergeSort = (Strategy) new MergeSort();
        test.runTests(mergeSort, "Merge Sort");

        Strategy insertionSort = (Strategy) new InsertionSort();
        test.runTests(insertionSort, "Insertion Sort");
    }

    public void runTests(Strategy s, String sortname) throws InvalidArrayLengthException
    {
        System.out.println("Testing " + sortname + ":");

        int[] optimisticArray = new int[1000];
        int[] pessimisticArray = new int[1000];
        int[] expectedArray = new int[1000];

        for(int i = 0; i < 1000; i++)
        {
            optimisticArray[i] = i + 1;
        }

        for(int i = 0; i < 1000; i++)
        {
            pessimisticArray[i] = 1000 - i;
        }

        Random rand = new Random();
        for(int i = 0; i < 1000; i++)
        {
            expectedArray[i] = rand.nextInt(1000) + 1;
        }


        System.out.println(sortname + " - optimistic: ");
        testSort(s, optimisticArray.clone());

        System.out.println(sortname + " - pessimistic: ");
        testSort(s, pessimisticArray.clone());

        System.out.println(sortname + " - expected: ");
        testSort(s, expectedArray.clone());

        System.out.println();
    }

    private void testSort(Strategy strategy, int[] array) throws InvalidArrayLengthException
    {
        Context context = new Context(strategy);
        context.executeStrategy(array);
    }
*/
}
