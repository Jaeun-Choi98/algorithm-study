package 그래프이론.이분그래프;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
그래프 이론, 이분 그래프
[problem](https://www.acmicpc.net/problem/1707)

모든 정점이 연결되어 있다고 가정하지말자..
 */

public class Main {
    static ArrayList<Integer>[] grap;
    static boolean flag;
    static int[] colors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while(tc!=0){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            grap = new ArrayList[v];
            colors = new int[v];

            for(int i=0;i<v;i++){
                grap[i] = new ArrayList<>();
            }
            for(int i=0;i<e;i++){
                st = new StringTokenizer(br.readLine());
                int sta = Integer.parseInt(st.nextToken())-1;
                int end = Integer.parseInt(st.nextToken())-1;
                grap[sta].add(end);
                grap[end].add(sta);
            }

            flag = false;

            //모든 노드들이 연결되어 있다는 가정이 없음
            for(int i=0;i<v;i++){
                if(flag) {
                    break;
                }
                if(colors[i] != 0) continue;
                dfs(i,1);
            }

            if(flag){
                bw.write("NO\n");
            }else{
                bw.write("YES\n");
            }
            tc--;
        }

        bw.flush();
        bw.close();
        br.close();

    }
    static public void dfs(int v, int color){
        colors[v] = color;
        for(int i=0;i<grap[v].size();i++){
            if(colors[grap[v].get(i)] == color){
                flag = true;
                return;
            }
            if(colors[grap[v].get(i)] != 0) continue;
            dfs(grap[v].get(i),-colors[v]);
        }
    }
}
