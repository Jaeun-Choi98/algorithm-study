package DFS와BFS.파이프옮기기1;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    dfs, 백트래킹
    [problem](https://www.acmicpc.net/problem/17070)
     */
    static int n;
    static int[][] map;

    //가로
    static int[] dx1 = {0,1};
    static int[] dy1 = {1,1};

    //세로
    static int[] dx3 = {1,1};
    static int[] dy3 = {0,1};

    //대각선
    static int[] dx2 = {0,1,1};
    static int[] dy2 = {1,1,0};
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        res = 0;
        dfs(1,0,1);

        System.out.println(res);
        br.close();
    }

    public static void dfs(int stat, int x, int y){
        if(x==n-1 && y==n-1){
            res += 1;
            return;
        }

        //가로
        if(stat == 1){
            for(int i=0;i<2;i++){
                int nx = x + dx1[i];
                int ny = y + dy1[i];
                if(nx>=n || ny >=n || map[nx][ny] == 1) continue;
                if(i==1){
                    if(map[nx-1][ny] == 1 || map[nx][ny-1] == 1) continue;
                }
                dfs(i+1,nx,ny);
            }
        }//대각선
        else if(stat == 2){
            for(int i=0;i<3;i++){
                int nx = x + dx2[i];
                int ny = y + dy2[i];
                if(nx>=n || ny >=n || map[nx][ny] == 1) continue;
                if(i==1){
                    if(map[nx-1][ny] == 1 || map[nx][ny-1] == 1) continue;
                }
                dfs(i+1,nx,ny);
            }
        }//세로
        else {
            for(int i=0;i<2;i++){
                int nx = x + dx3[i];
                int ny = y + dy3[i];
                if(nx>=n || ny >=n || map[nx][ny] == 1) continue;
                if(i==1){
                    if(map[nx-1][ny] == 1 || map[nx][ny-1] == 1) continue;
                }
                if(i==0) dfs(3,nx,ny);
                else dfs(i+1,nx,ny);
            }
        }
    }
}
