package SortingAlgorithms;
import Exeptions.*;

public interface Strategy
{
    void sort(int[]array) throws InvalidArrayLengthException;
}
