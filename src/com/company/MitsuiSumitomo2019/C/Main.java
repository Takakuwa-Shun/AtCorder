package com.company.MitsuiSumitomo2019.C;

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

    // -1:まだ　0:むり　1:可能
    private int dp[];

    @Override
    public void solve(PrintWriter out, FastScanner sc) {
        this.dp = new int[100001];
        Arrays.fill(this.dp, -1);
        for (int i=0; i<100; i++) {
            this.dp[i] = 0;
        }
        for (int i=100; i<=105; i++) {
            this.dp[i] = 1;
        }
        int X = sc.nextInt();
        int ans = calDp(X);
        if (ans == 1) {
            out.println(1);
        } else {
            out.println(0);
        }
    }

    private int calDp(final int x) {
        if (this.dp[x] == 0) {
            return 0;
        }
        if (this.dp[x] == 1) {
            return 1;
        }
        if (calDp(x-100) == 1){
            dp[x] = 1;
            return 1;
        }
        if (calDp(x-101) == 1){
            dp[x] = 1;
            return 1;
        }
        if (calDp(x-102) == 1){
            dp[x] = 1;
            return 1;
        }
        if (calDp(x-103) == 1){
            dp[x] = 1;
            return 1;
        }
        if (calDp(x-104) == 1){
            dp[x] = 1;
            return 1;
        }
        if (calDp(x-105) == 1){
            dp[x] = 1;
            return 1;
        }
        dp[x] = 0;
        return 0;
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
