package 동적계획법.구간합구하기;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] d = new int[100001];

    public static void main(String[] args) {
        int N,M;
        int i,j;
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt(); M = scanner.nextInt();
        for(int idx = 1; idx<N+1; idx++){
            d[idx] = scanner.nextInt() + d[idx-1];
        }

        for(int ct=0;ct<M;ct++){
            i = scanner.nextInt(); j = scanner.nextInt();
            System.out.println(d[j]-d[i-1]);
        }

    }
}
