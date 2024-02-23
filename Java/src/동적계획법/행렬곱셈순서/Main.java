package 동적계획법.행렬곱셈순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/11049)
     */
    static int n;
    static int[][] data;
    static Integer[][] d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data = new int[n+1][2];
        d = new Integer[n+1][n+1];
        StringTokenizer st;
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
        }
        System.out.println(topDown(1,n));
        br.close();
    }

    static int topDown(int s, int e){
        if(s==e) return 0;
        if(d[s][e] != null) return d[s][e];
        for(int i=s;i<e;i++){
            int val = topDown(s,i) + topDown(i+1,e) + (data[s][0] * data[i+1][0] * data[e][1]);
            if(d[s][e]==null) d[s][e] = val;
            else d[s][e] = Math.min(d[s][e], val);
        }
        return d[s][e];
    }
}
