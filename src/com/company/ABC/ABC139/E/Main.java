package com.company.ABC.ABC139.E;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        FastScanner sc = new FastScanner();
        Strategy strategy = new MyStrategy();
        strategy.solve(out, sc);
        out.flush();
    }
}

class MyStrategy implements Strategy {
    @Override
    public void solve(PrintWriter out, FastScanner sc) {
        int N = sc.nextInt();
        Queue<Integer>[] queues = new Queue[N];
        int sum = 0;
        for (int i=0; i<N; i++) {
            sum += i+1;
            queues[i] = new LinkedList<>();
            for (int j=0; j<N-1; j++) {
                queues[i].add(sc.nextInt());
            }
        }
        boolean[] fights = new boolean[N];
        Arrays.fill(fights, false);
        int days = 0;
        boolean can = false;
        int fin = 0;
        while (fin < sum) {
            for(int i=0; i<N; i++) {
                if (!fights[i] && queues[i].size() > 0) {
                    int partner = queues[i].peek() - 1;
                    if (!fights[partner]) {
                        if (i == queues[partner].peek() - 1) {
                            can = true;
                            queues[i].poll();
                            queues[partner].poll();
                            fights[i] = true;
                            fights[partner] = true;
                            if (queues[i].size() == 0) {
                                fin += i+1;
                            }
                            if (queues[partner].size() == 0) {
                                fin += partner +1;
                            }
                        }
                    }
                }
            }
            if(!can) {
                out.println(-1);
                return;
            }
            Arrays.fill(fights, false);
            can = false;
            days += 1;
        }
        out.println(days);
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