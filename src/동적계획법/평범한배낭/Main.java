package 동적계획법.평범한배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/12865)
     */
    static int n, k;
    static int[][] data;
    static int[][] d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        data = new int[n][2];
        d = new int[n+1][k+1];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
        }

        // bottom-up
        for(int i=0;i<n;i++){
            for(int j=1;j<=k;j++){
                if(data[i][0]>j) d[i+1][j] = d[i][j];
                else d[i+1][j] = Math.max(d[i][j], d[i][j-data[i][0]] + data[i][1]);
            }
        }

        System.out.println(d[n][k]);
        br.close();
    }

}
