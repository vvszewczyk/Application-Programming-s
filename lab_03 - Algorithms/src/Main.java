import static SortingAlgorithms.Tester.runExercise1;
import static McQueen.Lightning.runExercise2;
import static MergeAndMedian.M8M.runExercise3;
import static TripleSum.TripleSum.runExercise4;
import static SpiralMatrix.SpiralMatrix.runExercise5;

import Exeptions.InvalidArrayLengthException;
import Exeptions.InvalidMatrixDimensionsException;
import Exeptions.InvalidStringLength;

public class Main
{
    public static void main(String[] args) throws InvalidArrayLengthException, InvalidStringLength, InvalidMatrixDimensionsException
    {
        runExercise1();
        runExercise2();
        runExercise3();
        runExercise4();
        runExercise5();
    }
}
