package com.company.ABC.ABC146.D;

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
        List<Integer>[] tree = new List[N];
        int[] color = new int[N];
        int[] a = new int[N-1];
        int[] b = new int[N-1];
        for(int i=0; i<N; i++) {
            tree[i] = new ArrayList<>();
            color[i] = 1;
        }
        int K = 0;
        for(int i=0; i<N-1; i++) {
            a[i] = sc.nextInt()-1;
            b[i] = sc.nextInt()-1;
            tree[a[i]].add(b[i]);
            tree[b[i]].add(a[i]);

            if (tree[a[i]].size() > K) {
                K = tree[a[i]].size();
            }
            if (tree[b[i]].size() > K) {
                K = tree[b[i]].size();
            }
        }
        out.println(K);
        Queue<Integer> que = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        while (que.size() != 0) {

        }
        for(int i=0; i<N-1; i++) {
            int c = color[a[i]];
            if (c < color[b[i]]) {
                c = color[b[i]];
            }
            color[a[i]]++;
            color[b[i]]++;
            sb.append(c);
            sb.append("\n");
        }
        out.println(sb.toString());
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
