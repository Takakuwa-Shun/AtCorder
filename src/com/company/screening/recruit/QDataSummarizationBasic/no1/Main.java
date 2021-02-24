package com.company.screening.recruit.QDataSummarizationBasic.no1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        Path accessLogPath = Paths.get("/Users/takakuwashun/app/exam_4e6f3fb00/Q-DataSummarization-Basic/no1/data/accesslog_01.csv");
        Path botListPath = Paths.get("/Users/takakuwashun/app/exam_4e6f3fb00/Q-DataSummarization-Basic/no1/data/botlist_01.csv");
        Path answerPath = Paths.get("/Users/takakuwashun/app/exam_4e6f3fb00/Q-DataSummarization-Basic/no1/data/accesslog_01.answer.csv");
        Map<String, List<String>> dateToIpMap = createDateToIpMap(accessLogPath);
        Set<String> botIpSets = createBotIpSets(botListPath);
        List<Result> answer = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : dateToIpMap.entrySet()) {
            List<String> accessLogs = entry.getValue();
            int allCnt = accessLogs.size();
            accessLogs.removeAll(botIpSets);
            int validCnt = accessLogs.size();
            if (((float)validCnt / (float)allCnt) >= 0.5) {
                answer.add(new Result(Integer.parseInt(entry.getKey()), validCnt));
            }
        }
        answer.sort(Comparator.comparing(Result::getDate));
        for (Result result : answer) {
            System.out.println(result);
        }
//        List<String> result = answer.stream().map(Result::toString).collect(Collectors.toList());
//        try {
//            Files.write(answerPath, result, StandardOpenOption.CREATE);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private static Map<String, List<String>> createDateToIpMap(Path path) throws IOException {
        List<String> accessLogs = Files.readAllLines(path);
        Map<String, List<String>> dateToIpMap = new HashMap<>();
        for (int i = 1; i < accessLogs.size(); i++) {
            String[] arr = accessLogs.get(i).split(",");
            String date = arr[2].substring(0, 8);
            String ip = arr[1];
            if (dateToIpMap.containsKey(date)) {
                dateToIpMap.get(date).add(ip);
            } else {
                List<String> list = new ArrayList<>();
                list.add(ip);
                dateToIpMap.put(date, list);
            }
        }
        return dateToIpMap;
    }

    private static Set<String> createBotIpSets(Path path) throws IOException {
        return Files.lines(path)
                .skip(1)
                .map(line -> line.split(",")[1])
                .collect(Collectors.toSet());
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