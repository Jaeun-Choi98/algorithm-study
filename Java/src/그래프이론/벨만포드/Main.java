package 그래프이론.벨만포드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    벨만포드 알고리즘
    [problem](https://www.acmicpc.net/problem/11657)
     */
    static int v, e;
    static long[] d;
    static int[][] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        d = new long[v+1];
        edges = new int[e][3];


        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        for (int i=0;i<=v;i++) d[i] = Integer.MAX_VALUE;
        if(bellamFord()) System.out.println(-1);
        else{
            for(int i=1;i<=v;i++){
                if(i==1) continue;
                if(d[i] == Integer.MAX_VALUE) System.out.println(-1);
                else System.out.println(d[i]);
            }
        }
        br.close();
    }

    static boolean bellamFord(){
        boolean isNegativeCycle = false;
        d[1] = 0;
        for(int i=0;i<v;i++){
            for(int j=0;j<e;j++){
                int from = edges[j][0];
                int to = edges[j][1];
                int val = edges[j][2];
                if(d[from] == Integer.MAX_VALUE) continue;
                if(d[to] > d[from] + val ){
                    d[to] = d[from] + val;
                    if(i==v-1){
                        isNegativeCycle = true;
                    }
                }
            }
        }

        return isNegativeCycle;
    }
}
