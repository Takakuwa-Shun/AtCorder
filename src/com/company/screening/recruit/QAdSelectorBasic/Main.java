package com.company.screening.recruit.QAdSelectorBasic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Path segmentsPath = Paths.get("/Users/takakuwashun/app/exam_4e6f3fb00/Q-AdSelector-Basic/data/segments.csv");
        Path summaryPath = Paths.get("/Users/takakuwashun/app/exam_4e6f3fb00/Q-AdSelector-Basic/data/summary.csv");
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        Solution solution = new Solution(N, M, segmentsPath, summaryPath);
        while (true) {
            String input = scanner.next();
            if ("bye".equals(input)) {
                return;
            } else if (input.startsWith("u:")) {
                int x = Integer.parseInt(input.substring(2));
                int y = solution.handleA(x);
                System.out.println("a:" + y);
            } else if (input.startsWith("c:")) {
                int z = Integer.parseInt(input.substring(2));
                solution.handleC(z == 1);
            }
        }
    }


}

class Solution {
    private final int N;
    private final int M;
    private final int[] userIdToSegmentMatrix = new int[1000];
    private final Summary[][] summaryMatrix = new Summary[1000][10];
    private int x;
    private int y;

    public Solution(int n, int m, Path segmentsPath, Path summaryPath) throws IOException {
        this.N = n;
        this.M = m;
        Files.lines(segmentsPath)
                .map(line -> line.split(","))
                .forEach(arr -> {
                    int userId = Integer.parseInt(arr[0]);
                    int segment = Integer.parseInt(arr[1]);
                    this.userIdToSegmentMatrix[userId - 1] = segment;
                });
        Files.lines(summaryPath)
                .map(line -> line.split(","))
                .forEach(arr -> {
                    int userId = Integer.parseInt(arr[0]);
                    int adId = Integer.parseInt(arr[1]);
                    Summary summary = new Summary(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
                    this.summaryMatrix[userId - 1][adId - 1] = summary;
                });
        this.x = 0;
        this.y = 0;
    }

    int handleA(int x) {
        this.x = x;
        this.y = 1;
        return this.y;
    }

    public void handleC(boolean clicked) {
        Summary summary = this.summaryMatrix[this.x - 1][this.y - 1];
        if (summary == null) {
            return;
        }
        summary.incrementDisplayCnt();
        if (clicked) {
            summary.incrementClickCnt();;
        }
        this.x = 0;
        this.y = 0;
    }
}

class Summary {
    private int displayCnt;
    private int clickCnt;

    public Summary(int displayCnt, int clickCnt) {
        this.displayCnt = displayCnt;
        this.clickCnt = clickCnt;
    }

    public void incrementDisplayCnt() {
        this.displayCnt++;
    }

    public void incrementClickCnt() {
        this.clickCnt++;
    }
}