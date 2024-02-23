package 동적계획법.파이프옮기기2;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    dfs, 백트래킹, 다이나믹 프로그래밍
    [problem](https://www.acmicpc.net/problem/17069)

    DFS와BFS.내리막길.Main과 비슷하다. DFS와 DP가 합쳐진 문제.
     */
    static int n;
    static int[][] map;
    static long[][][] d;
    //가로
    static int[] dx1 = {0,1};
    static int[] dy1 = {1,1};

    //세로
    static int[] dx3 = {1,1};
    static int[] dy3 = {0,1};

    //대각선
    static int[] dx2 = {0,1,1};
    static int[] dy2 = {1,1,0};
    static long res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        d = new long[4][n][n];

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                d[1][i][j] = -1;
                d[2][i][j] = -1;
                d[3][i][j] = -1;
            }
        }
        res = dfs(1,0,1);

        /*for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(d[1][i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(d[2][i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(d[3][i][j]);
            }
            System.out.println("");
        }*/
        System.out.println(res);
        br.close();
    }

    public static long dfs(int stat, int x, int y){
        if(x==n-1 && y==n-1){
            return 1;
        }

        d[stat][x][y] = 0;

        //가로
        if(stat == 1){
            for(int i=0;i<2;i++){
                int nx = x + dx1[i];
                int ny = y + dy1[i];
                if(nx>=n || ny >=n || map[nx][ny] == 1) continue;
                if(i==1){
                    if(map[nx-1][ny] == 1 || map[nx][ny-1] == 1) continue;
                }
                if(d[i+1][nx][ny] != -1) {
                    d[stat][x][y] += d[i+1][nx][ny];
                    continue;
                }
                d[stat][x][y] += dfs(i+1,nx,ny);
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
                if(d[i+1][nx][ny] != -1) {
                    d[stat][x][y] += d[i+1][nx][ny];
                    continue;
                }
                d[stat][x][y] += dfs(i+1,nx,ny);
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
                if(i==0) {
                    if(d[3][nx][ny] != -1) {
                        d[stat][x][y] += d[3][nx][ny];
                        continue;
                    }
                    d[stat][x][y] += dfs(3,nx,ny);
                }
                else {
                    if(d[i+1][nx][ny] != -1){
                        d[stat][x][y] += d[i+1][nx][ny];
                        continue;
                    }
                    d[stat][x][y] += dfs(i+1,nx,ny);
                }
            }
        }

        return d[stat][x][y];
    }
}
