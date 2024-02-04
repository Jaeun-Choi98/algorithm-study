package 동적계획법.크리보드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/11058)
     */
    static long[] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        d = new long[101];
        for(int i=1;i<=6;i++) d[i] = i;
        for(int i=7;i<=100;i++){
            for(int j=2;j<5;j++){
                d[i] = Math.max(d[i], d[i-(j+1)]*j);
            }
        }
        System.out.println(d[Integer.parseInt(br.readLine())]);
        br.close();
    }
}
