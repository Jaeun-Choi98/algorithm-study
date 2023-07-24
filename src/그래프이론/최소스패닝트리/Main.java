package 그래프이론.최소스패닝트리;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
그래프이론, 최소스패닝트리(MST).feat(크루스칼알고리즘)
[problem](https://www.acmicpc.net/problem/1197)
 */

public class Main {
    static int v, e;
    static ArrayList<Integer[]>[] grap;
    static int result;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        grap = new ArrayList[v];
        check = new boolean[v];

        for(int i=0;i<v;i++){
            grap[i] = new ArrayList<>();
        }

        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());
            int sta = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int val = Integer.parseInt(st.nextToken());

            grap[sta].add(new Integer[]{end,val});
            grap[end].add(new Integer[]{sta,val});
        }
        result = 0;
        prioQue(0);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void prioQue(int start){
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((i1,i2)->i1[1]-i2[1]);
        pq.add(new Integer[]{0,0});
        while(!pq.isEmpty()){
            Integer[] cur = pq.poll();
            int end = cur[0];
            int val = cur[1];
            if(check[end]) continue;
            check[end] = true;
            result += val;
            for(int i=0;i<grap[end].size();i++){
                //if (check[grap[end].get(i)[0]]) continue;
                pq.add(new Integer[]{grap[end].get(i)[0], grap[end].get(i)[1]});
            }
        }
    }
}
