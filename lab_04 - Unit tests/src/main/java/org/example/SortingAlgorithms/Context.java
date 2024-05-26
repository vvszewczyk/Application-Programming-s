package org.example.SortingAlgorithms;

import org.example.Exeptions.InvalidArrayLengthException;

import static java.lang.StringTemplate.STR;

public class Context
{
    private final Strategy strategy;

    public Context(org.example.SortingAlgorithms.Strategy strategy)
    {
        this.strategy = strategy;
    }

    public void executeStrategy(int[] array) throws InvalidArrayLengthException
    {
        long startTime = System.nanoTime();
        strategy.sort(array);
        long endTime = System.nanoTime();
        System.out.println(STR."Execution time: \{endTime - startTime} ns");
    }
}
