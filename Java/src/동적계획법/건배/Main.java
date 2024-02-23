package 동적계획법.건배;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/1970)
     */
    static int N;
    static int[] data;
    static int[][] d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        data = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) data[i] = Integer.parseInt(st.nextToken());
        d = new int[N+1][N+1];
        for(int i=0;i<N+1;i++) Arrays.fill(d[i],-1);
        System.out.println(search(1,N));
        /*for(int i=1;i<=N;i++) {
            for(int j=i;j<=N;j++){
                System.out.printf("%d ",d[i][j]);
            }
            System.out.println("");
        }*/
        br.close();
    }

    static int search(int s, int e){
        if(s>=e) return 0;
        if(d[s][e] != -1) return d[s][e];
        d[s][e] = 0;
        for(int i=s;i<=e;i++){
            int val = 0;
            if(s!=i && data[s]==data[i]) val = 1;
            d[s][e] = Math.max(d[s][e],val+search(s+1,i-1)+search(i+1,e));
        }

        return d[s][e];
    }
}
