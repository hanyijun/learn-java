package hanyijun.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int three = nums[i] + nums[j] + nums[k];
                if (three == 0) {
                    List<Integer> result = new ArrayList<Integer>();
                    result.add(nums[i]);
                    result.add(nums[j]);
                    result.add(nums[k]);
                    resultList.add(result);
                    j++;
                    k--;
                    while (j < nums.length - 1 && nums[j] == nums[j - 1]) j++;
                    while (k > 0 && nums[k] == nums[k + 1]) k--;
                    continue;
                }
                if (three > 0) {
                    k--;
                    continue;
                }
                if (three < 0) {
                    j++;
                }
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 1, 1, 1, 2, -1, 4}));
    }
}
