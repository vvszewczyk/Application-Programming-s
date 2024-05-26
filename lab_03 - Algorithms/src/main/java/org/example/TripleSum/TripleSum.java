package org.example.TripleSum;
import org.example.Exeptions.InvalidArrayLengthException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TripleSum
{
    public List<List<Integer>> threeSum(int[] nums) throws InvalidArrayLengthException
    {
        if(nums.length <= 3)
        {
            throw new InvalidArrayLengthException("Array must contain at least 3 elements");
        }

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        // Iteracja przez liczby z pierwszym wskaźnikiem.
        for (int i = 0; i < nums.length - 2; i++)
        {
            if (i == 0 || nums[i] != nums[i - 1])
            {
                int low = i + 1, high = nums.length - 1, sum = 0 - nums[i];

                while (low < high)
                {
                    if (nums[low] + nums[high] == sum)
                    {
                        result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        while (low < high && nums[low] == nums[low+1]) low++;
                        while (low < high && nums[high] == nums[high-1]) high--;
                        low++;
                        high--;
                    }
                    else if (nums[low] + nums[high] < sum)
                    {
                        low++;
                    }
                    else
                    {
                        high--;
                    }
                }
            }
        }
        return result;
    }

    /*public static void runExercise4() throws InvalidArrayLengthException
    {
        TripleSum threeSumSolver = new TripleSum();

        int[] numsExample1 = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> resultExample1 = threeSumSolver.threeSum(numsExample1);
        System.out.println("Trójki sumujące się do zera dla tablicy [-1, 0, 1, 2, -1, -4]: " + resultExample1);

        int[] numsExample2 = {0, -1, 2, -3, 1};
        List<List<Integer>> resultExample2 = threeSumSolver.threeSum(numsExample2);
        System.out.println("Trójki sumujące się do zera dla tablicy [0, -1, 2, -3, 1]: " + resultExample2);
    }*/
}
