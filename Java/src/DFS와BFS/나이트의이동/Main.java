package DFS와BFS.나이트의이동;

import java.io.*;
import java.net.Inet4Address;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /*
    완전탐색, BFS: DFS로 해결하려고 하면, 경로를 중복해서 탐색해야하는 경우가 생긴다..
                  그래서, 이 문제의 경우 큐를 이용한 BFS로 푸는 것이 유용하다.
    [problem](https://www.acmicpc.net/problem/7562)
     */
    static int n,res;
    static int[] dx = {-2,-1,1,2,2,1,-1,-2};
    static int[] dy = {-1,-2,-2,-1,1,2,2,1};
    static boolean[][] check;
    static Integer[] start,end;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        start = new Integer[2];
        end = new Integer[2];

        for(int i=0;i<tc;i++){
            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            start[0] = Integer.parseInt(st.nextToken());
            start[1] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            end[0] = Integer.parseInt(st.nextToken());
            end[1] = Integer.parseInt(st.nextToken());

            res = Integer.MAX_VALUE;
            check = new boolean[n][n];
            search(start);
            bw.write(res + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(Integer[] cur){
        Queue<Integer[]> que = new LinkedList<>();
        que.add(new Integer[]{cur[0],cur[1],0});
        if(cur[0] == end[0] && cur[1] == end[1]) {
            res = 0;
            return;
        }
        check[cur[0]][cur[1]] = true;
        while (!que.isEmpty()){
            Integer[] buf = que.poll();
            for(int i=0;i<8;i++){
                int nx = buf[0] + dx[i];
                int ny = buf[1] + dy[i];
                if(nx<0||ny<0||nx>=n||ny>=n||check[nx][ny]) continue;
                if(nx == end[0] && ny == end[1]){
                    res = Math.min(res , buf[2]+1);
                    continue;
                }
                check[nx][ny] = true;
                que.add(new Integer[]{nx,ny,buf[2]+1});
            }
        }

    }
}
