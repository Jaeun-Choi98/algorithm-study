package 백트래킹.부분수열의합;

import java.util.Scanner;

public class Main {

    static int N, S;
    static int num = 0;
    static int[] data;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt(); S = scanner.nextInt();
        data = new int[N+1];

        for(int i=1;i<N+1;i++) data[i] = scanner.nextInt();

        func(0,0);
        if(S == 0){
            System.out.println(num - 1);
        }
        else System.out.println(num);

    }

    static void func(int start, int tot){
        if(start == N){
            if(tot == S) num++;
            return;
        }
        func(start+1, tot);
        func(start+1,tot+data[start+1]);
    }

}
