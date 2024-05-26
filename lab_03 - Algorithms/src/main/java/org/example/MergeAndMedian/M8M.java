package org.example.MergeAndMedian;

import org.example.Exeptions.InvalidArrayLengthException;

public class M8M
{
    public static double merged(int[] nums1, int[] nums2) throws InvalidArrayLengthException
    {
        if(nums1.length == 0 && nums2.length == 0)
        {
            throw new InvalidArrayLengthException("Cannot count median from empty arrays");
        }

        if (nums1.length > nums2.length)
        {
            return merged(nums2, nums1);
        }

        int x = nums1.length;
        int y = nums2.length;
        int low = 0;
        int high = x;

        while (low <= high)
        {
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];
            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX)
            {
                //the correct division has been found
                if ((x + y) % 2 == 0)
                {
                    return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                }
                else
                {
                    return Math.max(maxLeftX, maxLeftY);
                }
            }
            else if (maxLeftX > minRightY)
            {
                //move left
                high = partitionX - 1;
            }
            else
            {
                //move right
                low = partitionX + 1;
            }
        }
        throw new IllegalArgumentException("Cannot find division");
    }

        /*public static void runExercise3() throws InvalidArrayLengthException
        {
            int[] arr1;
            int[] arr3;
            int[] arr4;
            int[] arr2;

            arr1 = new int[]{1, 3};
            arr2 = new int[]{2};
            arr3 = new int[]{1, 2};
            arr4 = new int[]{3, 4};


            double merged = merged(arr1, arr2);
            double merged2 = merged(arr3, arr4);

            System.out.println("\nSize: " + (arr1.length+arr2.length) + " median: " + merged);
            System.out.println("Size: " + (arr1.length+arr2.length) + " median: " + merged2 + "\n");
        }*/
}
