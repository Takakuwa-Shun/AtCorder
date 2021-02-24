package com.company.screening.line.q1;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String S = "foobarbaz";
        System.out.println(solution.solution(S));
    }

}

class Solution {
    public int solution(String S) {
        Set<Character> set = new HashSet<>();
        Set<Character> duplicateSet = new HashSet<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (set.contains(c)) {
                duplicateSet.add(c);
            } else {
                set.add(c);
            }
        }
        if (duplicateSet.size() > 0) {
            return duplicateSet.size();
        } else {
            return 0;
        }
    }
}