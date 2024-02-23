package bruteforce.테트로미노;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
/*
브루트포스, 시물레이션
[problem](https://www.acmicpc.net/problem/14500)
 */
    static int n,m;
    static int[][] map;
    static boolean[][] check;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};

    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        check = new boolean[n][m];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = Integer.MIN_VALUE;
        Queue<Integer[]> que = new LinkedList<>();


        for(int i=0;i<n;i++) {
            for (int j = 0; j < m; j++) {
                check[i][j] = true;
                search(i, j, 1, map[i][j]);
                check[i][j] = false;

                //DFS로 "ㅗ"모양을 탐색할 수 없다.
                int[][] buf = new int[4][3];
                int cnt = 0;
                for (int l = 0; l < 4; l++) {
                    int nx = i + dx[l];
                    int ny = j + dy[l];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                    buf[l][0] = nx;
                    buf[l][1] = ny;
                    buf[l][2] = 1;
                    cnt++;
                }

                if(cnt == 4){
                    int sum1 = map[buf[0][0]][buf[0][1]] + map[buf[1][0]][buf[1][1]] + map[buf[2][0]][buf[2][1]];
                    int sum2 = map[buf[0][0]][buf[0][1]] + map[buf[1][0]][buf[1][1]] + map[buf[3][0]][buf[3][1]];
                    int sum3 = map[buf[0][0]][buf[0][1]] + map[buf[2][0]][buf[2][1]] + map[buf[3][0]][buf[3][1]];
                    int sum4 = map[buf[1][0]][buf[1][1]] + map[buf[2][0]][buf[2][1]] + map[buf[3][0]][buf[3][1]];
                    int sum = 0;
                    sum = Math.max(sum,sum1);
                    sum = Math.max(sum,sum2);
                    sum = Math.max(sum,sum3);
                    sum = Math.max(sum,sum4);
                    res = Math.max(res,sum+map[i][j]);
                }else if(cnt == 3){
                    int sum = 0;
                    for(int l=0;l<4;l++){
                        if(buf[l][2]==1){
                            sum += map[buf[l][0]][buf[l][1]];
                        }
                    }
                    res = Math.max(res,sum +map[i][j]);
                }


            }
        }
        bw.write(res + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int x, int y, int cnt, int sum){
        if(cnt == 4){
            res = Math.max(res,sum);
            //System.out.println(res);
            return;
        }

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0||ny<0||nx>=n||ny>=m) continue;
            if(check[nx][ny]) continue;
            check[nx][ny] = true;
            search(nx,ny,cnt+1,sum+map[nx][ny]);
            check[nx][ny] = false;
        }
    }
}
