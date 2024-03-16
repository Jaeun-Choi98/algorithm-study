package 동적계획법.욕심쟁이판다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    dp, 완전탐색(DFS)
    [problem](https://www.acmicpc.net/problem/1937)
     */
    static int n;
    static int[][] d, datas;
    static int[] dx={0,1,0,-1}, dy={1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        datas = new int[n][n];
        d = new int[n][n];
        StringTokenizer st;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                datas[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                search(i,j);
            }
        }
        int res = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                res = Math.max(res,d[i][j]);
            }
        }
        System.out.printf("%d\n",res);

        br.close();
    }
    static int search(int x, int y){

        if(d[x][y]!=0) return d[x][y];

        d[x][y] = 1;

        int[] buf = new int[4];
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0||ny<0||nx>=n||ny>=n) continue;
            if(datas[x][y] >= datas[nx][ny]) continue;
            buf[i] += search(nx,ny);
        }
        int max = 0;
        for(int i=0;i<4;i++){
            max = Math.max(max,buf[i]);
        }
        d[x][y] += max;
        return d[x][y];
    }
}
