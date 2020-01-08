package com.company.ABC.ABC123.D;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Logic logic = new Logic2();
        logic.solve(sc);
    }
}

interface Logic {
    void solve(Scanner sc);
}

class Logic1 implements Logic {

    @Override
    public void solve(Scanner sc) {
        int X = sc.nextInt();
        int Y = sc.nextInt();
        int Z = sc.nextInt();
        int K = sc.nextInt();

        long[] a = new long[X];
        long[] b = new long[Y];
        long[] c = new long[Z];

        for (int i=0; i<X; i++) {
            a[i] = sc.nextLong();
        }
        for (int i=0; i<Y; i++) {
            b[i] = sc.nextLong();
        }
        for (int i=0; i<Z; i++) {
            c[i] = sc.nextLong();
        }
        PriorityQueue<Long> queue1 = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i=0; i<X; i++) {
            for (int j=0; j<Y; j++) {
                queue1.add(a[i]+b[j]);
            }
        }
        PriorityQueue<Long> queue2 = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i=0; i<K; i++) {
            long e;
            if (queue1.size() > 0) {
                e = queue1.poll();
            } else {
                break;
            }
            for (int j=0; j<Z; j++) {
                queue2.add(e+c[j]);
            }
        }
        for (int i=0; i<K; i++) {
            System.out.println(queue2.poll());
        }
    }
}


class Logic2 implements Logic {

    @Override
    public void solve(Scanner sc) {
        int X = sc.nextInt();
        int Y = sc.nextInt();
        int Z = sc.nextInt();
        int K = sc.nextInt();

        long[] A = new long[X];
        long[] B = new long[Y];
        long[] C = new long[Z];

        for (int i=0; i<X; i++) {
            A[i] = sc.nextLong();
        }
        for (int i=0; i<Y; i++) {
            B[i] = sc.nextLong();
        }
        for (int i=0; i<Z; i++) {
            C[i] = sc.nextLong();
        }

        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);

        PriorityQueue<Long[]> queue = new PriorityQueue<Long[]>(new Comparator<Long[]>() {
            @Override
            public int compare(Long[] o1, Long[] o2) {
                long l1 = o1[0];
                long l2 = o2[0];
                return Long.compare(l1, l2) * -1;
            }
        });

        Long[] e = {A[X-1] + B[Y-1] + C[Z-1], Long.valueOf(X-1), Long.valueOf(Y-1),Long.valueOf(Z-1)};
        Set<String> set = new HashSet<>();
        set.add(createSet(X-1, Y-1, Z-1));
        queue.add(e);
        for (int i=0; i<K; i++) {
            e = queue.poll();
            System.out.println(e[0]);
            long a = e[1];
            long b = e[2];
            long c = e[3];
            String set1 = createSet(a, b, c-1);
            String set2 = createSet(a, b-1, c);
            String set3 = createSet(a-1, b, c);

            if (!set.contains(set1) && c>=1) {
                Long[] ee = {A[(int)a] + B[(int)b] + C[(int)c-1], a, b, c-1};
                set.add(set1);
                queue.add(ee);
            }
            if (!set.contains(set2) && b>=1) {
                Long[] ee = {A[(int)a] + B[(int)b-1] + C[(int)c], a, b-1, c};
                set.add(set2);
                queue.add(ee);
            }
            if (!set.contains(set3) && a>=1) {
                Long[] ee = {A[(int)a-1] + B[(int)b] + C[(int)c], a-1, b, c};
                set.add(set3);
                queue.add(ee);
            }
        }
    }

    private String createSet(long a, long b, long c) {
        StringBuilder sb = new StringBuilder();
        sb.append(a);
        sb.append("_");
        sb.append(b);
        sb.append("_");
        sb.append(c);
        return sb.toString();
    }
}
