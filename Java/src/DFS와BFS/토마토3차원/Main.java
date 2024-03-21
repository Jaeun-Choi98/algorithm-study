package DFS와BFS.토마토3차원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /*
    bfs
    [problem](https://www.acmicpc.net/problem/7569)
     */

    static int n,m,h,cnt;
    static int[][][] datas;
    static int[] dh = {-1, 1, 0, 0, 0, 0}, dx = {0, 0, 1, -1, 0, 0}, dy = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        datas = new int[h][n][m];
        cnt = 0;

        Queue<Integer[]> que1 = new LinkedList<>();
        Queue<Integer[]> buf = new LinkedList<>();
        for(int i=0;i<h;i++){
            for(int j=0;j<n;j++){
                st = new StringTokenizer(br.readLine());
                for(int l=0;l<m;l++){
                    datas[i][j][l] = Integer.parseInt(st.nextToken());
                    if(datas[i][j][l] == 0) cnt++;
                    if(datas[i][j][l] == 1) que1.add(new Integer[]{i,j,l});
                }
            }
        }

        // 0 이면 익지 않은, 1 이면 익은, -1 이면 빈
        int res = 0;
        boolean isEnd = false;
        while (!isEnd){
            isEnd = true;
            while (!que1.isEmpty()){
                Integer[] bf = que1.poll();
                for(int i=0;i<6;i++){
                    int nh = bf[0] + dh[i];
                    int nx = bf[1] + dx[i];
                    int ny = bf[2] + dy[i];
                    if(nh<0 || nh >= h || nx < 0 || nx >=n || ny <0 || ny>=m) continue;
                    if(datas[nh][nx][ny] == 0) {
                        datas[nh][nx][ny] = 1;
                        cnt--;
                        isEnd = false;
                        buf.add(new Integer[]{nh,nx,ny});
                    }
                }
            }
            if(!isEnd) {
                res++;
                while (!buf.isEmpty()){
                    que1.add(buf.poll());
                }
            }
        }

        if(cnt == 0) System.out.printf("%d", res);
        else System.out.printf("%d", -1);
        br.close();
    }
}
