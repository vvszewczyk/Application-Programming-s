package SortingAlgorithms;

import Exeptions.InvalidArrayLengthException;

public class Context
{
    private final Strategy strategy;

    public Context(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public void executeStrategy(int[] array) throws InvalidArrayLengthException
    {
        long startTime = System.nanoTime();
        strategy.sort(array);
        long endTime = System.nanoTime();
        System.out.println("Czas wykonania: " + (endTime - startTime) + " ns");
    }
}
