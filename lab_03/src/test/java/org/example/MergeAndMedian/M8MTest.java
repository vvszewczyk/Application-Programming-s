package org.example.MergeAndMedian;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class M8MTest
{
    @Test
    void testMergedEvenTotalElements() throws org.example.Exeptions.InvalidArrayLengthException
    {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        double expectedMedian = 2.5;
        assertEquals(expectedMedian, M8M.merged(nums1, nums2));
    }

    @Test
    void testMergedOddTotalElements() throws org.example.Exeptions.InvalidArrayLengthException
    {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4, 5};
        double expectedMedian = 3.0;
        assertEquals(expectedMedian, M8M.merged(nums1, nums2));
    }

    @Test
    void testMergedWithEmptyArrays()
    {
        int[] nums1 = {};
        int[] nums2 = {};
        assertThrows(org.example.Exeptions.InvalidArrayLengthException.class, () -> M8M.merged(nums1, nums2));
    }

    @Test
    void testMergedWithOneEmptyArray() throws org.example.Exeptions.InvalidArrayLengthException
    {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {};
        double expectedMedian = 2.0;
        assertEquals(expectedMedian, M8M.merged(nums1, nums2));
    }

}