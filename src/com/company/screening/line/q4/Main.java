package com.company.screening.line.q4;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {0, 3, -60, 4, 0};
        System.out.println(solution.solution(A));
    }

}

class Solution {
    public int solution(int[] A) {
        if (A.length < 3) {
            return -1;
        } else if (A.length == 3) {
            return A[0] * A[1] * A[2];
        }

        // 以下　A.length >= 4
        Arrays.sort(A);

        // 全て負
        if (A[A.length-1] < 0) {
            return A[A.length-1] * A[A.length-2] * A[A.length-3];
        }

        int max1 = 0;
        boolean enable1 = A[0] < 0 && A[1] < 0 && A[A.length-1] >= 0;
        if (enable1) {
            max1 = A[0] * A[1] * A[A.length-1];
        }

        int max2 = 0;
        boolean enable2 = A[A.length-1] >= 0 && A[A.length-2] >= 0 && A[A.length-3] >= 0;
        if (enable2) {
            max2 = A[A.length-1] * A[A.length-2] * A[A.length-3];
        }

        if (enable1 && enable2) {
            return Math.max(max1, max2);
        } else if (enable1) {
            return max1;
        } else {
            return max2;
        }
    }
}