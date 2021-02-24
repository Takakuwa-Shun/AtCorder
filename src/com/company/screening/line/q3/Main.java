package com.company.screening.line.q3;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] S = {"add 5 3", "add 1 2", "get 5", "evict", "get 1", "remove 5", "exit"};
        int[] res = solution.solution(S);
        for (int r : res) {
            System.out.println(r);
        }
    }

}

class Solution {
    public int[] solution(String[] A) {
        MyMap<Integer, Integer> map = new MyMapImpl<>();
        int[] result = new int[A.length];
        int next = 0;
        for (String s : A) {
            String[] arr = s.split(" ");
            int key;
            int val;
            switch (arr[0]) {
                case "evict":
                    map.evict();
                    break;
                case "add":
                    key = Integer.parseInt(arr[1]);
                    val = Integer.parseInt(arr[2]);
                    map.add(key, val);
                    break;
                case "get":
                    key = Integer.parseInt(arr[1]);
                    try {
                        result[next] = map.get(key);
                    } catch (Exception e) {
                        result[next] = -1;
                    }
                    next++;
                    break;
                case "remove":
                    key = Integer.parseInt(arr[1]);
                    try {
                        result[next] = map.remove(key);
                    } catch (Exception e) {
                        result[next] = -1;
                    }
                    next++;
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("unknown type : " + arr[0]);
            }
        }
        int[] ans = new int[next];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = result[i];
        }
        return ans;
    }
}

interface MyMap<K, V> {
    void add(K key, V val);
    V get(K key) throws Exception;
    V remove(K key) throws Exception;
    void evict();
}

class MyMapImpl<K, V> implements MyMap<K, V>{

    private final Map<K, V> map;
    private LinkedList<K> updatedList;

    public MyMapImpl() {
        super();
        this.map = new HashMap<>();
        this.updatedList = new LinkedList<>();
    }

    @Override
    public void add(K key, V val) {
        this.update(key);
        this.map.put(key, val);
    }

    @Override
    public V get(K key) throws Exception {
        if (this.map.containsKey(key)) {
            this.update(key);
            return this.map.get(key);
        }
        throw new Exception();
    }

    @Override
    public V remove(K key) throws Exception {
        if (this.map.containsKey(key)) {
            return this.map.remove(key);
        }
        throw new Exception();
    }

    @Override
    public void evict() {
        K key = this.updatedList.removeFirst();
        this.map.remove(key);
    }

    private void update(K key) {
        this.updatedList.remove(key);
        this.updatedList.addLast(key);
    }
}