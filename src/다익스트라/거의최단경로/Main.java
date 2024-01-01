package 다익스트라.거의최단경로;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    /*
    다익스트라 알고리즘
    [problem](https://www.acmicpc.net/problem/5719)
     */
    static int v,e,start,end;
    static List<Integer[]>[] map;
    static int[][] d;
    static boolean[][] check;
    static List<Integer>[] track;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        while (true){
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            if(v==0 && e==0) break;
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            map = new ArrayList[v];
            track = new ArrayList[v];
            d = new int[v][2];
            for(int i=0;i<v;i++) {
                map[i] = new ArrayList<Integer[]>();
                //track[i] = new ArrayList<>();
                d[i][0] = Integer.MAX_VALUE;
                d[i][1] = Integer.MAX_VALUE;
            }
            int u,w,p;
            for(int i=0;i<e;i++){
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());
                p = Integer.parseInt(st.nextToken());

                map[u].add(new Integer[]{w,p});
            }
            check = new boolean[v][v];

            search(0);
            checking(end);
            search(1);
            if(d[end][1] == Integer.MAX_VALUE) bw.write(-1 + "\n");
            else bw.write(d[end][1]+"\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void checking(int t){
        if(start==t) return;
        if(track[t]!=null){
            for(int i=0, size=track[t].size();i<size;i++){
                int next = track[t].get(i);
                if(!check[next][t]){
                    check[next][t] = true;
                    checking(next);
                }
            }
        }

    }

    public static void search(int c){
        PriorityQueue<Integer[]> que = new PriorityQueue<>((p1,p2)->p2[1]-p1[1]);
        d[start][c] = 0;
        que.add(new Integer[]{start,d[start][c]});
        while (!que.isEmpty()){
            Integer[] buf = que.poll();
            int cur = buf[0];
            int dis = buf[1];

            if(dis>d[cur][c]) continue;
            for(int i=0, size=map[cur].size();i<size;i++){
                int next = map[cur].get(i)[0];
                if(check[cur][next]) continue;

                int nextDis = map[cur].get(i)[1];
                if(dis+nextDis<d[next][c]){
                    d[next][c] = dis + nextDis;
                    // 여기서 새로운 리스트를 생성하는 이유: 0->1 비용:10 이고, 0->2->1 비용이 3이라면
                    // track[1]은 삭제되어야 한다.
                    track[next] = new ArrayList<>();
                    track[next].add(cur);
                    que.add(new Integer[]{next,d[next][c]});
                }else if(dis+nextDis==d[next][c]) track[next].add(cur);
            }
        }
    }
}
