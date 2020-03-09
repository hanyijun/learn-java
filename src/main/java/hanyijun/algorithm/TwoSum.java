package hanyijun.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int num = target - cur;
            if (numMap.containsKey(num)) {
                return new int[]{i, numMap.get(num)};
            }
            numMap.put(cur, i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 11, 15,7}, 9)));
    }
}
