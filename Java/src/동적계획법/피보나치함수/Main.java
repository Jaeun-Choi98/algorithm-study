package 동적계획법.피보나치함수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /*
    dp, 피보나치
    [problem](https://www.acmicpc.net/problem/1003)
     */
    static int[][] d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        d = new int[41][2];
        d[0][0] = 1;
        d[1][1] = 1;
        pibo(40);
        while (tc--!=0){
            int n = Integer.parseInt(br.readLine());
            System.out.printf("%d %d\n",d[n][0],d[n][1]);
        }
        br.close();
    }

    static int[] pibo(int n){
        if(n==0) return new int[]{d[n][0],d[n][1]};
        if(n==1) return new int[]{d[n][0],d[n][1]};
        if(d[n][0] !=0 && d[n][1] !=0) return new int[]{d[n][0],d[n][1]};
        d[n][0] = pibo(n-1)[0] + pibo(n-2)[0];
        d[n][1] = pibo(n-1)[1] + pibo(n-2)[1];
        return new int[]{d[n][0],d[n][1]};
    }
}
