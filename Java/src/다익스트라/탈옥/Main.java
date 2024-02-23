package 다익스트라.탈옥;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    /*
    BFS, PriorityQueue
    [problem](https://www.acmicpc.net/problem/9376)
     */
    static int n,m,res;
    static char[][] map;
    static int[][][] d;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while (tc-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n=Integer.parseInt(st.nextToken());
            m=Integer.parseInt(st.nextToken());
            map =new char[n+2][m+2];
            d = new int[3][n+2][m+2];
            int[] d1 = new int[2];
            d1[0] = -1;
            int[] d2 = new int[2];
            for(int i=1;i<=n;i++){
                String buf = br.readLine();
                for(int j=1;j<=m;j++){
                    map[i][j] = buf.charAt(j-1);
                    if(map[i][j]=='$'){
                        if(d1[0] == -1) {
                            d1[0] = i;
                            d1[1] = j;
                        }else{
                            d2[0] = i;
                            d2[1] = j;
                        }
                        map[i][j] = '.';
                    }
                }
            }

            for(int i=0;i<m+2;i++){
                map[0][i] = '.';
                map[n+1][i] = '.';
            }
            for(int i=0;i<n+2;i++){
                map[i][0] = '.';
                map[i][m+1] = '.';
            }

            res = Integer.MAX_VALUE;
            search(0,0,0);
            search(1,d1[0],d1[1]);
            search(2,d2[0],d2[1]);

            for(int i=0;i<n+2;i++){
                for(int j=0;j<m+2;j++){
                    if(map[i][j] == '*') continue;
                    int buf = 0;
                    buf = d[0][i][j] + d[1][i][j] + d[2][i][j];
                    if(map[i][j] == '#') buf -= 2;

                    res = Math.min(buf,res);
                }
            }
            System.out.println(res);
        }

        br.close();
    }

    public static void search(int p, int x, int y){
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((p1,p2)->p1[2]-p2[2]);
        for(int i=0;i<n+2;i++){
            for(int j=0;j<m+2;j++){
                d[p][i][j] = Integer.MAX_VALUE;
            }
        }
        d[p][x][y] = 0;
        pq.add(new Integer[]{x,y,d[p][x][y]});

        while (!pq.isEmpty()){
            Integer[] buf = pq.poll();
            for(int i=0;i<4;i++){
                int nx = buf[0] + dx[i];
                int ny = buf[1] + dy[i];
                if(nx <0 || ny< 0 || nx>=n+2 || ny >=m+2 || map[nx][ny] == '*') continue;
                if(d[p][nx][ny] != Integer.MAX_VALUE) continue;
                if(map[nx][ny] == '#') {
                    d[p][nx][ny] = buf[2] + 1;
                } else {
                    d[p][nx][ny] = buf[2];
                }
                pq.add(new Integer[]{nx,ny,d[p][nx][ny]});
            }

        }

    }

}
