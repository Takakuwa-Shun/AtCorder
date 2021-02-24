package com.company.screening.line.q2;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String S = "-91437.3i";
        System.out.println(solution.solution(S));
    }

}

class Solution {
    public String solution(String S) {
        int startIdx = S.indexOf(".");
        if (startIdx == -1) {
            startIdx = S.length() - 1;
        } else {
            startIdx -= 1;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = S.length()-1; i >= 0 ; i--) {
            char c = S.charAt(i);
            if ((startIdx > i) && ((startIdx - i) % 3 == 0) && (c != '-')) {
                builder.insert(0, ",");
            }
            builder.insert(0, c);
        }
        return builder.toString();
    }
}