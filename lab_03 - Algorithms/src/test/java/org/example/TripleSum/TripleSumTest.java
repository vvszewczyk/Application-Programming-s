package org.example.TripleSum;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class TripleSumTest
{
    @Test
    void testThreeSumBasicCase() throws org.example.Exeptions.InvalidArrayLengthException
    {
        TripleSum tripleSum = new TripleSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> expected = List.of(List.of(-1, -1, 2), List.of(-1, 0, 1));
        assertEquals(expected, tripleSum.threeSum(nums));
    }

    @Test
    void testThreeSumWithRepeatingNumbers() throws org.example.Exeptions.InvalidArrayLengthException
    {
        TripleSum tripleSum = new TripleSum();
        int[] nums = {-1, -1, -1, 2, 2, 0, 1, 1};
        List<List<Integer>> expected = List.of(List.of(-1, -1, 2), List.of(-1, 0, 1));
        assertEquals(expected, tripleSum.threeSum(nums));
    }

    @Test
    void testThreeSumNoSolution() throws org.example.Exeptions.InvalidArrayLengthException
    {
        TripleSum tripleSum = new TripleSum();
        int[] nums = {1, 2, -2, -1};
        List<List<Integer>> expected = List.of();
        assertEquals(expected, tripleSum.threeSum(nums));
    }

    @Test
    void testThreeSumArrayTooShort()
    {
        TripleSum tripleSum = new TripleSum();
        int[] nums = {1, 2};
        assertThrows(org.example.Exeptions.InvalidArrayLengthException.class, () -> tripleSum.threeSum(nums));
    }
}