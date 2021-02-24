package com.company.screening.recruit.QDataSummarizationBasic.no2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        Path accessLogPath = Paths.get("/Users/takakuwashun/app/exam_4e6f3fb00/Q-DataSummarization-Basic/no2/data/accesslog_02.csv");
        Path answerPath = Paths.get("/Users/takakuwashun/app/exam_4e6f3fb00/Q-DataSummarization-Basic/no2/data/accesslog_02.answer.csv");
        Map<Integer, Map<String, Integer>> timeToIpCntMap = createTimeToIpCntMap(accessLogPath);
        List<Result> answer = new ArrayList<>();
        for (Map.Entry<Integer, Map<String, Integer>> entry : timeToIpCntMap.entrySet()) {
            int validCnt = entry.getValue().values()
                    .stream()
                    .mapToInt(a -> a)
                    .filter(cnt -> cnt < 10)
                    .sum();
            answer.add(new Result(entry.getKey(), validCnt));
        }
        answer.sort(Comparator.comparing(Result::getDate));
        List<String> result = answer.stream().map(Result::toString).collect(Collectors.toList());
        try {
            Files.write(answerPath, result, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<Integer, Map<String, Integer>> createTimeToIpCntMap(Path path) throws IOException {
        List<String> accessLogs = Files.readAllLines(path);
        Map<Integer, Map<String, Integer>> resMap = new HashMap<>();
        for (int i = 1; i < accessLogs.size(); i++) {
            String[] arr = accessLogs.get(i).split(",");
            int time = Integer.parseInt(arr[2]);
            String ip = arr[1];
            if (resMap.containsKey(time)) {
                Map<String, Integer> map = resMap.get(time);
                if (map.containsKey(ip)) {
                    map.put(ip, map.get(ip) + 1);
                } else {
                    map.put(ip, 1);
                }
            } else {
                Map<String, Integer> map = new HashMap<>();
                map.put(ip, 1);
                resMap.put(time, map);
            }
        }
        return resMap;
    }
}

class Result {
    private final int date;
    private final int cnt;

    public Result(int date, int cnt) {
        this.date = date;
        this.cnt = cnt;
    }

    public int getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date + "," + cnt;
    }
}