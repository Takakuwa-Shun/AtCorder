package com.company.ABC.ABC167.C;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

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

    int N;
    int M;
    int X;
    int[] c;
    int[][] A;

    private int buy(int[] sum, int idx, int cost) {
        if (idx == N) {
            for (int j = 0; j < M; j++) {
                if(sum[j] < X) {
                    return -1;
                }
            }
            return cost;
        }
        int[] sum1 = new int[M];
        int[] sum2 = new int[M];
        for (int j = 0; j < M; j++) {
            sum1[j] = sum[j] + A[idx][j];
            sum2[j] = sum[j];
        }

        int c1 = buy(sum1, idx+1, cost + c[idx]);
        int c2 = buy(sum2, idx+1, cost);
        if (c1 == -1 && c2 == -1) {
            return -1;
        } else if (c1 == -1) {
            return c2;
        } else if (c2 == -1) {
            return c1;
        }

        if (c1 < c2) {
            return c1;
        } else {
            return c2;
        }
    }

    @Override
    public void solve(PrintWriter out, FastScanner sc) {
        N = sc.nextInt();
        M = sc.nextInt();
        X = sc.nextInt();
        c = new int[N];
        int[] sum = new int[M];
        Arrays.fill(sum, 0);
        A = new int[N][M];

        for (int i = 0; i < N; i++) {
            c[i] = sc.nextInt();
            for (int j = 0; j < M; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        int c = buy(sum ,0, 0);
        out.println(c);
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
