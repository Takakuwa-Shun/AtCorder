package com.company.ABC.ABC127.D;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        FastScanner sc = new FastScanner();
        Strategy strategy = new ImpStrategy();
        strategy.solve(out, sc);
        out.flush();
    }
}

class ImpStrategy implements Strategy {
    @Override
    public void solve(PrintWriter out, FastScanner sc) {
        int N = sc.nextInt();
        int M = sc.nextInt();
        PriorityQueue<Long> queue = new PriorityQueue<>(N, Comparator.naturalOrder());
        for (int i=0; i<N; i++) {
            queue.offer(sc.nextLong());
        }
        TreeMap<Long, Integer> map = new TreeMap(Comparator.reverseOrder());
        for (int i=0; i<M; i++) {
            int B = sc.nextInt();
            long C = sc.nextLong();
            if (map.containsKey(C)) {
                map.put(C,map.get(C)+B);
            } else {
                map.put(C,B);
            }
        }
        long sum = 0;
        Long val = queue.poll();
        outer: while (val != null) {
            Map.Entry<Long, Integer> entry = map.pollFirstEntry();
            if (entry == null) {
                sum += val;
                break;
            }
            for (int i=0; i<entry.getValue(); i++) {
                if (val == null) {
                    break outer;
                }
                if (val >= entry.getKey()) {
                    sum += val;
                    break outer;
                }
                sum += entry.getKey();
                val = queue.poll();
            }
        }
        for(long l: queue) {
            sum += l;
        }
        out.println(sum);
    }
}

class MyStrategy implements Strategy {
    @Override
    public void solve(PrintWriter out, FastScanner sc) {
        int N = sc.nextInt();
        int M = sc.nextInt();
        PriorityQueue<Long> queue = new PriorityQueue<>(N, Comparator.naturalOrder());
        for (int i=0; i<N; i++) {
            queue.offer(sc.nextLong());
        }
        for (int i=0; i<M; i++) {
            int B = sc.nextInt();
            long C = sc.nextLong();
            for(int j=0; j<B; j++) {
                Long val = queue.peek();
                if (val == null || val >= C) {
                    break;
                }
                queue.poll();
                queue.add(C);
            }
        }
        long sum = 0;
        Iterator<Long> iterator  =queue.iterator();
        while (iterator.hasNext()){
            sum += iterator.next();
        }
        out.println(sum);
    }
}

interface Strategy {
    void solve(PrintWriter out, FastScanner sc);
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
