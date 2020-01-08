package com.company.ABC.ABC146.C;

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

        long A = sc.nextLong();
        long B = sc.nextLong();
        long X = sc.nextLong();

        if (X < A + B) {
            out.println(0);
            return;
        }

        long N = X/A;
        long costN = calN(N, A, B);

        if (N > 1_000_000_000) {
            N = 1_000_000_000;
            costN = calN(N, A, B);
            if (costN <= X) {
                out.println(N);
                return;
            }
        }

        if (costN > X) {
            while (costN > X) {
                N -= 100_000_000;
                costN = calN(N, A, B);
            }
            while (costN <= X) {
                N += 10_000_000;
                costN = calN(N, A, B);
            }
            while (costN > X) {
                N -= 1_000_000;
                costN = calN(N, A, B);
            }
            while (costN <= X) {
                N += 100_000;
                costN = calN(N, A, B);
            }
            while (costN > X) {
                N -= 100_000;
                costN = calN(N, A, B);
            }
            while (costN <= X) {
                N += 10_000;
                costN = calN(N, A, B);
            }
            while (costN > X) {
                N -= 1_000;
                costN = calN(N, A, B);
            }
            while (costN <= X) {
                N += 100;
                costN = calN(N, A, B);
            }
            while (costN > X) {
                N--;
                costN = calN(N, A, B);
            }
        } else {
            while (costN <= X) {
                N += 100_000_000;
                costN = calN(N, A, B);
            }
            while (costN > X) {
                N -= 10_000_000;
                costN = calN(N, A, B);
            }
            while (costN <= X) {
                N += 1_000_000;
                costN = calN(N, A, B);
            }
            while (costN > X) {
                N -= 100_000;
                costN = calN(N, A, B);
            }
            while (costN <= X) {
                N += 10_000;
                costN = calN(N, A, B);
            }
            while (costN > X) {
                N -= 1_000;
                costN = calN(N, A, B);
            }
            while (costN <= X) {
                N += 100;
                costN = calN(N, A, B);
            }
            while (costN > X) {
                N--;
                costN = calN(N, A, B);
            }
        }
        out.println(N);
    }

    private long calN(long N , long A, long B) {
        return A*N + B * dN(N);
    }

    private long dN(long N) {
        long cnt = 1;
        while (N >= 10) {
         N /= 10;
         cnt++;
        }
        return cnt;
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
