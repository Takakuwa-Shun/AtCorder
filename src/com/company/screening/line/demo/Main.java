package com.company.screening.line.demo;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {1, 3, 6, 4, 1, 2};
        System.out.println(solution.solution(A));
    }

}

class Solution {
    public int solution(int[] A) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i : A) {
            if (!set.contains(i)) {
                queue.add(i);
                set.add(i);
            }
        }
        int cur = 1;
        while (queue.size() > 0) {
            int val = queue.poll();
//            System.out.println(val);
            if (val <= 0) {
                continue;
            }
            if (val > cur + 1) {
                cur += 1;
                return cur;
            } else {
                cur = val;
            }
        }
        if (cur <= 0) {
            return 1;
        }
        return cur;
    }
}