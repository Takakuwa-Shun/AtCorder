package com.company.Dowango6th.B;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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

    final long BIG_NUM = 1_000_000_007;

    @Override
    public void solve(PrintWriter out, FastScanner sc) {
        int N = sc.nextInt();
        List<Integer> x = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            x.add(sc.nextInt());
        }

        // 操作
        for (int i = 1; i <= N-1; i++) {

        }
    }

    private long calc(List<Integer> x) {
        int size = x.size();
        long sum = x.get(x.size()-1) - x.get(0);
        for (int i = 0; i < size; i++) {
            int[] a = new int[190];
        }
        return sum;
    }



    private void connect(int[] x, Integer[] refX, int k) {
        refX[k] = refX[k+1];
    }

    private int getRightNeighborDistance(int k, int[] x) {
        return x[k+1] - x[k];
    }

    private long factorial(int N) {
        long sum = 1;
        for (int i = 2; i <= N; i++) {
            sum = (sum * i) % BIG_NUM;
        }
        return sum;
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
