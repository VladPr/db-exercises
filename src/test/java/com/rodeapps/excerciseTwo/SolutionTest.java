package com.rodeapps.excerciseTwo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    Solution solution = new Solution();

    @Test
    void testExample1() {
        int[] A = {130, 191, 200, 10};
        assertEquals(140, solution.solution(A));
    }

    @Test
    void testExample2() {
        int[] A = {405, 45, 300, 300};
        assertEquals(600, solution.solution(A));
    }

    @Test
    void testExample3() {
        int[] A = {50, 222, 49, 52, 25};
        assertEquals(-1, solution.solution(A));
    }

    @Test
    void testExample4() {
        int[] A = {30, 909, 3190, 99, 3990, 9009};
        assertEquals(9918, solution.solution(A));
    }

    @Test
    void testNegativeNumbersStillWork() {
        int[] A = {-33, -909, -3193, -3990, -9009};
        assertEquals(-3226, solution.solution(A)); // from earlier debug
    }

    @Test
    void testEmptyArray() {
        int[] A = {};
        assertEquals(-1, solution.solution(A));
    }

    @Test
    void testSingleElement() {
        int[] A = {777};
        assertEquals(-1, solution.solution(A));
    }

    @Test
    void testTwoMatchingDigitsSameNumber() {
        int[] A = {121, 121}; // same number twice → should be valid
        assertEquals(242, solution.solution(A));
    }

    @Test
    void testLargeRandomInput() {
        int[] A = TestGenerator.generateTestCase(10_000, -10_000, 10_000);
        int result = solution.solution(A);
        // We don't assert a value here — just make sure it doesn't crash
        assertTrue(result >= -20_000 && result <= 20_000);
    }
}
