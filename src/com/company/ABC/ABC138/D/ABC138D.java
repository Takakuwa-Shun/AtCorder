package com.company.ABC.ABC138.D;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class ABC138D {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        FastScanner sc = new FastScanner();
        int N = sc.nextInt();
        int Q = sc.nextInt();
        Node[] nodes = new Node[N];

        for(Integer i=0; i<N; i++) {
            Node node  = new Node();
            nodes[i] = node;
        }

        for (int i=1; i<=N-1; i++) {
            Node A = nodes[sc.nextInt()-1];
            Node B = nodes[sc.nextInt()-1];
            B.parents.add(A);
            A.parents.add(B);
        }

        for (int i=1; i<=Q; i++) {
            Node P = nodes[sc.nextInt()-1];
            P.val += sc.nextLong();
        }

        nodes[0].calculateVal2(null, 0);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
//            Node next = nodes[i];
//            long val  = next.calculateVal();
//            sb.append(val);
            sb.append(nodes[i].val);
            sb.append(" ");
        }

        out.println(sb.toString());
        out.flush();
    }
}

class Node {
    int val = 0;
    long sum = 0;
    boolean calculated = false;
    List<Node> parents = new LinkedList<>();;

    Node() {
    }

    long calculateVal() {
        if (!calculated) {
            for (Node parent: parents) {
                this.sum += parent.calculateVal();
            }
            this.sum += this.val;
            this.calculated = true;
        }
        return this.sum;
    }

    void calculateVal2(Node from, int val) {
        this.val += val;
        for(Node node : parents) {
            if(from == node) {
                continue;
            }
            node.calculateVal2(this, this.val);
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", sum=" + sum +
                ", calculated=" + calculated +
                ", parents=" + parents +
                '}';
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