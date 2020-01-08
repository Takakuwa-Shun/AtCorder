package com.company.ABC.ABC130.D;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        FastScanner sc = new FastScanner();
        int N = sc.nextInt();
        long K = sc.nextLong();
        long cnt = 0;
        long[][] a = new long[2][N];
        a[0][0] = sc.nextLong();
        a[1][0] = a[0][0];
        int lagerIdx = 0;
        boolean find = false;
        if (a[1][0] >= K){
            find = true;
        }
        for (int i=1; i<N; i++) {
            a[0][i] = sc.nextLong();
            a[1][i] = a[1][i-1] + a[0][i];
            if (!find && a[1][i] >= K){
                lagerIdx = i;
                find = true;
            }
        }
        if (find) {
            cnt += N - lagerIdx;
//            out.println("f = "  + cnt);
        }
        long sum = a[1][lagerIdx];
        for (int i=0; i<N; i++) {
            sum -= a[0][i];
            if (sum >= K) {
                cnt += N - lagerIdx;
//                out.println("s = "  + cnt);
                continue;
            }
            long sumImp = sum;
            boolean find2 = false;
            for (int j=lagerIdx+1; j<N; j++) {
                sumImp += a[0][j];
                if (sumImp >= K){
                    lagerIdx = j;
                    find2 = true;
                    sum = sumImp;
                    break;
                }
            }
            if (find2) {
                cnt += N - lagerIdx;
//                out.println("t = "  + cnt);
            } else {
                break;
            }
        }
        out.println(cnt);
        out.flush();
    }
}
//6 6 5 5 5 5 2 1 1

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