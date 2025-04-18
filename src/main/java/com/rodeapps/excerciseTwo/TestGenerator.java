package com.rodeapps.excerciseTwo;

public class TestGenerator {

    public static int[] generateTestCase(int size, int minValue, int maxValue) {
        int[] testCase = new int[size];
        for (int i = 0; i < size; i++) {
            testCase[i] = (int) (Math.random() * (maxValue - minValue + 1)) + minValue;
        }
        return testCase;
    }

    public static void main(String[] args) {
        int[] testCase = generateTestCase(10, -10000, 10000);
        for (int num : testCase) {
            System.out.print(num + " ");
        }
    }
}

