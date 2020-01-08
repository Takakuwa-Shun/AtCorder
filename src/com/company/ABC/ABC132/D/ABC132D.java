package com.company.ABC.ABC132.D;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class ABC132D {

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        FastScanner sc = new FastScanner();
        StringBuilder sb = new StringBuilder();
        long mod = 1000000007;
        int N = sc.nextInt();
        int K = sc.nextInt();

        long[][] combination = new long[N+2][];

        combination[0] = new long[1];
        combination[0][0] = 1;

        combination[1] = new long[2];
        combination[1][0] = 1;
        combination[1][1] = 1;

        for (int i = 2; i< N+2 ; i++ ){
            combination[i] = new long[i+1];
            combination[i][0] = 1;
            combination[i][i] = 1;
            for (int j=1; j<i; j++) {
                combination[i][j] = (combination[i-1][j-1] + combination[i-1][j]) % mod;
            }
        }

        long A = (N - K + 1) % mod;
        sb.append(A);
        sb.append("\n");
        for (int i=2; i<=K; i++) {
            if(N-K+1 < i) {
                sb.append(0);
                sb.append("\n");
                continue;
            }
            A = (combination[N-K+1][i] * combination[K-1][i-1]) % mod;
            sb.append(A);
            sb.append("\n");
        }
        out.println(sb.toString());
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