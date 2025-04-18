package com.rodeapps.excerciseTwo;

import java.util.*;

public class Solution {

    private static final int NO_VALID_PAIR = -1;
    private static final int LOWEST_VALUE = Integer.MIN_VALUE;

    private String getFirstAndLastDigitKey(int number) {
        number = Math.abs(number);
        int lastDigit = number % 10;
        while (number >= 10) {
            number /= 10;
        }
        int firstDigit = number;
        return firstDigit + "" + lastDigit;
    }

    public int solution(int[] A) {
        Map<String, int[]> map = new HashMap<>();

        for (int number : A) {
            String key = getFirstAndLastDigitKey(number);
            map.putIfAbsent(key, new int[]{LOWEST_VALUE, LOWEST_VALUE});
            int[] top2 = map.get(key);

            if (number > top2[0]) {
                top2[1] = top2[0];
                top2[0] = number;
            } else if (number > top2[1]) {
                top2[1] = number;
            }
        }

        int maxSum = LOWEST_VALUE;
        for (int[] pair : map.values()) {
            if (pair[1] != LOWEST_VALUE) {
                int sum = pair[0] + pair[1];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return (maxSum == LOWEST_VALUE) ? NO_VALID_PAIR : maxSum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A =    TestGenerator.generateTestCase(500000, -1000000, 1000000);

        System.out.println("Input: " + Arrays.toString(A));

        System.out.println("The solution is "+solution.solution(A));
    }
}
