package 동적계획법.계단오르기;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] d = new int[301];

    public static void main(String[] args) {

        int N;
        int[] score;
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        score = new int[N+1];

        for(int i=1;i<N+1;i++) score[i] = scanner.nextInt();
        //Arrays.stream(score).forEach(System.out::println);


        for(int i=1;i<N+1;i++){
            if(i==1) {
                d[i] = score[i];
                continue;
            }
            if(i==2) {
                d[i] = d[i-1] + score[i];
                continue;
            }
            d[i] = Math.max(d[i-3]+score[i-1],d[i-2]) + score[i];
        }

        System.out.println(d[N]);
    }
}
