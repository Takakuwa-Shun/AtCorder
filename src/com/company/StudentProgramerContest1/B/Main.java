package com.company.StudentProgramerContest1.B;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.TreeMap;

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
        long mod = 1000000007;
        int N  = sc.nextInt();
        int K  = sc.nextInt();
        TreeMap<Integer, Integer> map = new TreeMap(Comparator.naturalOrder());
        int a = sc.nextInt();
        map.put(a,1);
        long t = 0;
        long s = 0;
        for(int i=1; i<=N-1; i++) {
            a = sc.nextInt();
            if (map.containsKey(a)) {
                map.put(a, map.get(a) + 1);
            } else {
                map.put(a,1);
            }
            NavigableMap<Integer,Integer> nMap = map.tailMap(a, false);
            for (Integer key: nMap.keySet()) {
                t += nMap.get(key);
            }
            NavigableMap<Integer,Integer> nMap2 = map.headMap(a, false);
            for (Integer key: nMap2.keySet()) {
                s += nMap2.get(key);
            }
            out.println(nMap);
            out.println(nMap2);
        }
        out.println(t);
        out.println(s);
        out.println((((t+s) % mod) + ((s % mod) * ((K-1) * (K/2) % mod ) % mod)) % mod);
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