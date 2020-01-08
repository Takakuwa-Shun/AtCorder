package com.company.ABC.ABC129.D;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        FastScanner sc = new FastScanner();
        Strategy strategy = new SlowStrategy();
//        Strategy strategy = new CorrectStrategy();
        strategy.solve(out, sc);
        out.flush();
    }
}

class CorrectStrategy implements Strategy {
    @Override
    public void solve(PrintWriter out, FastScanner sc) {
        int H = sc.nextInt();
        int W = sc.nextInt();
    }
}

class SlowStrategy implements Strategy {
    @Override
    public void solve(PrintWriter out, FastScanner sc) {
        int H = sc.nextInt();
        int W = sc.nextInt();
        MoveInteger[][] rows  = new MoveInteger[H][W];
        MoveInteger[][] cols  = new MoveInteger[H][W];

        String S = sc.next();
        char cur = S.charAt(0);
        if (cur == '.') {
            rows[0][0] = new MoveInteger(1);
            cols[0][0] = new MoveInteger(1);
        } else {
            rows[0][0] = null;
            cols[0][0] = null;
        }
        for (int j=1; j<S.length(); j++) {
            cur = S.charAt(j);
            if (cur == '.') {
                if (rows[0][j-1] == null) {
                    rows[0][j] = new MoveInteger(1);
                } else {
                    rows[0][j] = rows[0][j-1].addI(1);
                }
                cols[0][j] = new MoveInteger(1);
            } else {
                rows[0][j] = null;
                cols[0][j] = null;
            }
        }

        for (int i=1; i<H; i++) {
            S = sc.next();
            cur = S.charAt(0);
            if (cur == '.') {
                if (cols[i-1][0] == null) {
                    cols[i][0] = new MoveInteger(1);
                } else {
                    cols[i][0] = cols[i-1][0].addI(1);
                }
                rows[i][0] = new MoveInteger(1);
            } else {
                rows[i][0] = null;
                cols[i][0] = null;
            }
            for (int j=1; j<S.length(); j++) {
                cur = S.charAt(j);
                if (cur == '.') {
                    if (cols[i-1][j] == null) {
                        cols[i][j] = new MoveInteger(1);
                    } else {
                        cols[i][j] = cols[i-1][j].addI(1);
                    }
                    if (rows[i][j-1] == null) {
                        rows[i][j] = new MoveInteger(1);
                    } else {
                        rows[i][j] = rows[i][j-1].addI(1);
                    }
                } else {
                    rows[i][j] = null;
                    cols[i][j] = null;
                }
            }
        }
        int max = 0;
//        for (int i=0; i<H; i++) {
//            out.println("rows " + i);
//            out.println(Arrays.toString(rows[i]));
//        }
//        for (int i=0; i<H; i++) {
//            out.println("cols " + i);
//            out.println(Arrays.toString(cols[i]));
//        }
        for (int i=0; i<H; i++) {
            for (int j=0; j<W; j++) {
                if (rows[i][j] == null || cols[i][j] == null) {
                    continue;
                }
                int val = rows[i][j].toInt() + cols[i][j].toInt() -1;
                if (max < val) {
                    max = val;
                }

            }
        }
        out.println(max);
    }
}

class MoveInteger {
    private int i;
    MoveInteger(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public MoveInteger addI(int i) {
        this.i += 1;
        return this;
    }

    public int toInt() {
        return this.i;
    }

    @Override
    public String toString() {
        return String.valueOf(i);
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