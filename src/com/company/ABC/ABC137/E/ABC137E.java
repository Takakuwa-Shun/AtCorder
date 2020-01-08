package com.company.ABC.ABC137.E;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class ABC137E {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        FastScanner sc = new FastScanner();
        int N = sc.nextInt();
        int M = sc.nextInt();
        int P = sc.nextInt();
        Graph graph = new Graph(N);
        for (int i=0; i<M; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            int C = sc.nextInt();
            Node nodeA = graph.getNode(A);
            Node nodeB = graph.getNode(B);
            Edge edge = new Edge(nodeA, nodeB, C-P);
            nodeA.addToEdge(edge);
            nodeB.addFromEdge(edge);
        }
        out.println(graph);
        out.flush();
    }
}

class Graph {
    private HashMap<Integer, Node> nodes;

    public Graph(int size) {
        HashMap<Integer, Node> map = new HashMap<>();
        for (Integer i=1; i<=size; i++) {
            map.put(i, new Node(i));
        }
        this.nodes = map;
    }

    public int getSize() {
        return nodes.size();
    }

    public void addNode(Node node) throws Exception {
        if (nodes.containsKey(node)) {
            throw new Exception("キーあるよ");
        } else {
            nodes.put(node.getNo(), node);
        }
    }

    public Node getNode(int no) {
        return nodes.get(no);
    }

    @Override
    public String toString() {
        return nodes.toString();
    }
}

class Edge {
    private Node fromNode;
    private Node toNode;
    private int value;

    public Edge(Node fromNode, Node toNode, int value) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(fromNode.getNo());
        sb.append(" -> ");
        sb.append(toNode.getNo());
        sb.append("(");
        sb.append(value);
        sb.append(")}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return getValue() == edge.getValue() &&
                Objects.equals(getFromNode(), edge.getFromNode()) &&
                Objects.equals(getToNode(), edge.getToNode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFromNode(), getToNode(), getValue());
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getToNode() {
        return toNode;
    }

    public void setToNode(Node toNode) {
        this.toNode = toNode;
    }

    public Node getFromNode() {
        return fromNode;
    }

    public void setFromNode(Node fromNode) {
        this.fromNode = fromNode;
    }
}

class Node {
    private List<Edge> fromEdges;
    private List<Edge> toEdges;
    private int no;

    public Node(int no) {
        this(new ArrayList<>(), new ArrayList<>(), no);
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node(List<Edge> fromEdges, List<Edge> toEdges, int no) {
        this.fromEdges = fromEdges;
        this.toEdges = toEdges;
        this.no = no;
    }

    public void addToEdge(Edge to) {
        this.toEdges.add(to);
    }

    public void addFromEdge(Edge from) {
        this.fromEdges.add(from);
    }

    public List<Edge> getToNeighborEdges() {
        return toEdges;
    }

    public List<Edge> getFromNeighborEdges() {
        return fromEdges;
    }

    public List<Node> getToNeighborNodes() {
        return toEdges.stream().map(to -> to.getToNode()).collect(Collectors.toList());
    }

    public List<Node> getFromNeighborNodes() {
        return fromEdges.stream().map(to -> to.getToNode()).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{from=");
        sb.append(fromEdges);
        sb.append(", to=");
        sb.append(toEdges);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return no == node.no &&
                Objects.equals(fromEdges, node.fromEdges) &&
                Objects.equals(toEdges, node.toEdges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromEdges, toEdges, no);
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