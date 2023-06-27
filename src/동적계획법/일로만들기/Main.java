package 동적계획법.일로만들기;

import java.util.Scanner;

public class Main {

    static int[] d = new int[1000001];

    public static void main(String[] args) {
        int N;
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        d[1] = 0;
        for(int i=2;i<=N;i++){
            d[i] = d[i-1] + 1;
            if(i%2==0) d[i] = Math.min(d[i],d[i/2]+1);
            if(i%3==0) d[i] = Math.min(d[i],d[i/3]+1);
        }
        System.out.println(d[N]);
    }

}
