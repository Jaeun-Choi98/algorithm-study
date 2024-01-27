package 동적계획법.일이삼더하기사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /*
    dp(bottom-up)
    [problem](https://www.acmicpc.net/problem/15989)
     */
    static int[][] d = new int[10001][4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        d[1][1] = 1;
        d[2][1] = 1;
        d[2][2] = 1;
        d[3][1] = 1;
        d[3][2] = 1;
        d[3][3] = 1;
        for(int i=4;i<10001;i++){
            d[i][1] = d[i-1][1];
            d[i][2] = d[i-2][1] + d[i-2][2];
            d[i][3] = d[i-3][1] + d[i-3][2] + d[i-3][3];
        }

        while (tc!=0){
            int tar = Integer.parseInt(br.readLine());
            System.out.println(d[tar][1]+d[tar][2]+d[tar][3]);
            tc--;
        }

        br.close();
    }
}
