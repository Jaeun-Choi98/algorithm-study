package DFS와BFS.내리막길;

import java.io.*;
import java.util.StringTokenizer;

/*
DFS, DP
[problem](https://www.acmicpc.net/problem/1520)

문제복기
1. 58번줄, 62번줄의 의미: 중복을 제거하기 위함. visit 처리를 따로 할 필요가 없음.
한 번 방문했더라도 그 지점에서 (n-1,m-1)에 도달하지 못하면 0을 리턴한다.
 */

public class Main {
    public static int n,m;
    public static int[][] map;
    public static int[][] dp;
    public static int[] dx = {0,0,-1,1};
    public static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dp = new int[n][m];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        bw.write(dfs(0,0) + "\n");
        bw.flush();
        bw.close();
        br.close();
        /*for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(dp[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }*/
    }

    public static int dfs(int x, int y){
        if(x==n-1 && y==m-1){
            dp[x][y] = 1;
            return 1;
        }

        if(dp[x][y] != -1){
            return dp[x][y];
        }

        dp[x][y] = 0;
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0||ny<0||nx>=n||ny>=m) continue;
            if(map[nx][ny]>=map[x][y]) continue;
            dp[x][y] += dfs(nx,ny);
        }

        return dp[x][y];
    }
}
