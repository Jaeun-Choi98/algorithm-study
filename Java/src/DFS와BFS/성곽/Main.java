package DFS와BFS.성곽;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /*
    BFS
    [problem](https://www.acmicpc.net/problem/2234)
     */
    static int n, m, res1, res2, res3;
    static int[][] datas;

    static int[] dx = {0,-1,0,1}, dy = {-1,0,1,0};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        datas = new int[n][m];
        visited = new boolean[n][m];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                datas[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Search12();
        Search3();

        br.close();
    }
    static void Search3(){
        res3 = res2;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                for(int k=0;k<4;k++){
                    int dir = (int)Math.pow(2,k);
                    if((datas[i][j] & dir)>0){
                        datas[i][j] -= dir;
                        res3 = Math.max(res3, BFS3(i,j));
                        datas[i][j] += dir;
                    }
                }
            }
        }
        System.out.println(res3);
    }
    static int BFS3(int x, int y){
        boolean[][] visited3 = new boolean[n][m];
        visited3[x][y] = true;
        int ret = 1;
        Queue<Integer[]> que = new LinkedList<>();
        que.add(new Integer[]{x,y});
        while (!que.isEmpty()){
            Integer[] buf = que.poll();
            for(int i=0;i<4;i++){
                int nx = buf[0] + dx[i];
                int ny = buf[1] + dy[i];
                if(nx < 0 || ny < 0 || nx >=n || ny >=m || visited3[nx][ny] ||(datas[buf[0]][buf[1]] & (int)Math.pow(2,i)) > 0) continue;
                visited3[nx][ny] = true;
                que.add(new Integer[]{nx,ny});
                ret++;
            }
        }
        return ret;
    }
    static int BFS(int x, int y){
        int ret = 1;
        Queue<Integer[]> que = new LinkedList<>();
        que.add(new Integer[]{x,y});
        while (!que.isEmpty()){
            Integer[] buf = que.poll();
            for(int i=0;i<4;i++){
                int nx = buf[0] + dx[i];
                int ny = buf[1] + dy[i];
                if(nx < 0 || ny < 0 || nx >=n || ny >=m || visited[nx][ny] ||(datas[buf[0]][buf[1]] & (int)Math.pow(2,i)) > 0) continue;
                visited[nx][ny] = true;
                que.add(new Integer[]{nx,ny});
                ret++;
            }
        }
        return ret;
    }
    static void Search12(){
        res2 = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(visited[i][j]) continue;
                visited[i][j] = true;
                res2 = Math.max(BFS(i,j),res2);
                res1++;
            }
        }
        System.out.println(res1);
        System.out.println(res2);
    }
}
