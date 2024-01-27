package 동적계획법.팰린드롬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/10942)
     */
    static int[] data;
    static boolean[][] d;
    static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) data[i] = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        d = new boolean[n][n];

        for(int i=0;i<n;i++) d[i][i] = true;
        for(int i=0;i<n-1;i++) {
            if(data[i] == data[i+1]) d[i][i+1] = true;
        }
        for(int i=2;i<n;i++){
            for(int j=0;j<n-i;j++){
                if(data[j] == data[j+i] && d[j+1][j+i-1]) d[j][j+i] = true;
            }
        }

        int s,e;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken())-1;
            e = Integer.parseInt(st.nextToken())-1;

            if(d[s][e]) sb.append("1\n");
            else sb.append("0\n");
        }
        System.out.println(sb);
        br.close();
    }
}
