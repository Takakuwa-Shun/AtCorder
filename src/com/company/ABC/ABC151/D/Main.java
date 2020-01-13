package com.company.ABC.ABC151.D;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
    int H, W;
    boolean[][] maze;
    boolean[][] visited;
    int[][][][] dist;
    int[] ans;

    @Override
    public void solve(PrintWriter out, FastScanner sc) {
        H = sc.nextInt();
        W = sc.nextInt();

        // trueの時通過可能
        maze = new boolean[H][W];
        visited = new boolean[H][W];
        dist = new int[H][W][H][W];
        ans = new int[H*W];
        for (int i = 0; i < H; i++) {
            String s = sc.next();
            for (int j = 0; j < W; j++) {
                if (s.charAt(j) == '#') {
                    maze[i][j] = false;
                } else {
                    maze[i][j] = true;
                }
                visited[i][j] = false;

                for (int k = 0; k < H; k++) {
                    for (int w = 0; w < W; w++) {
                        dist[i][j][k][w] = -1;
                    }
                }
            }
        }

        int idx = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    if (maze[i][j]) {
                        ans[idx] = 1;
                        search(i, j, idx);
                        idx++;
                    }
                }
            }
        }
        int max = 0;
        out.println(ans);
        for (int an : ans) {
            if (an > max) {
                max = an;
            }
        }
        out.println(max);
    }

    private void search(int h, int w, int idx) {

        // 上を調べる
        if (h > 0 && !visited[h-1][w]) {
            visited[h-1][w] = true;
            if (maze[h-1][w]) {
                ans[idx] += 1;
                search(h-1, w, idx);
            }
        }

        // 下を調べる
        if (h < (this.H-1) && !visited[h+1][w]) {
            visited[h+1][w] = true;
            if (maze[h+1][w]) {
                ans[idx] += 1;
                search(h+1, w, idx);
            }
        }

        // 左を調べる
        if (w  > 0 && !visited[h][w-1]) {
            visited[h][w-1] = true;
            if (maze[h][w-1]) {
                ans[idx] += 1;
                search(h, w-1, idx);
            }
        }

        // 右を調べる
        if (w < (this.W-1) && !visited[h][w+1]) {
            visited[h][w + 1] = true;
            if (maze[h][w + 1]) {
                ans[idx] += 1;
                search(h, w + 1, idx);
            }
        }
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
