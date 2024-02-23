package 동적계획법.괄호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/10422)
    어렵다..
     */
    static long d[] = new long[2501];
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        d[1] = 1;
        topDown(2500);

        while(tc!=0){
            int l = Integer.parseInt(br.readLine());
            if(l%2==0) System.out.println(d[l/2]);
            else System.out.println(0);
            tc--;
        }
        //for(int i=1;i<=10;i++) System.out.println(d[i]);
        br.close();
    }
    static long topDown(int idx){
        if(idx<=1) return d[1];
        if(d[idx] != 0) return d[idx];
        for(int i=1;i<idx;i++) d[idx] += topDown(idx-i) * topDown(i-1) %1000000007;
        d[idx] += topDown(idx-1);
        d[idx] %= 1000000007;
        return d[idx];
    }
}
