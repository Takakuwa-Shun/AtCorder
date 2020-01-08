package com.company.ABC.ABC131.D;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        FastScanner sc = new FastScanner();
        int N = sc.nextInt();
        TreeMap<Integer, PriorityQueue<Integer>> queueTreeMap = new TreeMap<>();
        for (int i=0; i<N; i++) {
            Integer A = sc.nextInt();
            Integer B = sc.nextInt();
            if (queueTreeMap.containsKey(B)) {
                PriorityQueue<Integer> queue = queueTreeMap.get(B);
                queue.add(A);
                queueTreeMap.put(B, queue);
            } else {
                PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
                queue.add(A);
                queueTreeMap.put(B, queue);
            }
        }
//        out.println(queueTreeMap);
        Integer time = 0;
        boolean can = true;
        Map.Entry<Integer, PriorityQueue<Integer>> entry = queueTreeMap.pollFirstEntry();
        first: while (entry != null){
            Integer B = entry.getKey();
            PriorityQueue<Integer> queue = entry.getValue();
            Integer A = queue.poll();
            while (A != null) {
                if (A + time <= B) {
                    time += A;
                } else {
                    can = false;
                    break first;
                }
                A = queue.poll();
            }
            entry = queueTreeMap.pollFirstEntry();
        }
        if (can) {
            out.println("Yes");
        } else {
            out.println("No");
        }
        out.flush();
    }
}

class FastScanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;
    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        }else{
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }
    private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
    private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
    public boolean hasNext() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte();}
    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
    public long nextLong() {
        if (!hasNext()) throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b) {
            throw new NumberFormatException();
        }
        while(true){
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            }else if(b == -1 || !isPrintableChar(b)){
                return minus ? -n : n;
            }else{
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }
    public int nextInt() {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }
    public double nextDouble() { return Double.parseDouble(next());}
}