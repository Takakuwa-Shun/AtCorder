package com.company.screening.recruit.QAdSelectorBasic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Path segmentsPath = Paths.get("/Users/takakuwa.s/takakuwa-app/exam_4e6f3fb00/Q-AdSelector-Basic/data/segments.csv");
        Path summaryPath = Paths.get("/Users/takakuwa.s/takakuwa-app/exam_4e6f3fb00/Q-AdSelector-Basic/data/summary.csv");
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
    private static final double EPSILON = 0.2;
    private final int n;
    private final int m;
    private final int[] userIdToSegmentMatrix = new int[1000];
    private final Summary[][] summaryMatrix = new Summary[10][10];
    private int segmentIdx;
    private int adIdx;
    private final Random rnd = new Random();

    public Solution(int n, int m, Path segmentsPath, Path summaryPath) throws IOException {
        this.n = n;
        this.m = m;
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
                    int displayCnt = Integer.parseInt(arr[2]);
                    int clickCnt = Integer.parseInt(arr[3]);
                    int segmentId = this.userIdToSegmentMatrix[userId - 1];
                    Summary summary = this.summaryMatrix[segmentId - 1][adId - 1];
                    if (summary == null) {
                        this.summaryMatrix[segmentId - 1][adId - 1] = new Summary(displayCnt, clickCnt);
                    } else {
                        summary.addClickAndDisplayCnt(displayCnt, clickCnt);
                        summary.calcProbability();
                    }
                });
        this.segmentIdx = -1;
        this.adIdx = -1;
    }

    int handleA(int x) {
        this.segmentIdx = this.userIdToSegmentMatrix[x] - 1;
        if (rnd.nextDouble() > EPSILON) {
            Summary[] summaries = this.summaryMatrix[this.segmentIdx];
            double probability = 0;
            for (int i = 0; i < m; i++) {
                if (summaries[i].getProbability() > probability) {
                    probability = summaries[i].getProbability();
                    this.adIdx = i;
                }
            }
        } else {
            this.adIdx = rnd.nextInt(m);
        }
        return this.adIdx + 1;
    }

    public void handleC(boolean clicked) {
        Summary summary = this.summaryMatrix[this.segmentIdx][this.adIdx];
        summary.incrementClickAndDisplayCnt(clicked);
        summary.calcProbability();
        this.segmentIdx = -1;
        this.adIdx = -1;
    }
}

class Summary {
    private int displayCnt;
    private int clickCnt;
    private double probability;

    public Summary(int displayCnt, int clickCnt) {
        this.displayCnt = displayCnt;
        this.clickCnt = clickCnt;
        this.calcProbability();
    }

    public double getProbability() {
        return probability;
    }

    public double calcProbability() {
        this.probability = (double)this.clickCnt / (double)this.displayCnt;
        return this.probability;
    }

    public void incrementClickAndDisplayCnt(boolean clicked) {
        this.displayCnt++;
        if (clicked) {
            this.clickCnt++;
        }
    }

    public void addClickAndDisplayCnt(int displayCnt, int clickCnt) {
        this.displayCnt += displayCnt;
        this.clickCnt += clickCnt;
    }
}
