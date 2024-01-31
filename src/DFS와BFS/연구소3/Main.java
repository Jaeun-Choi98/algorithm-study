package DFS와BFS.연구소3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
    bfs, combination
    [problem](https://www.acmicpc.net/problem/17142)
    문제를 잘 이해하자
     */
    static int n,m;
    static int[][] data;
    static int check;
    static List<Integer[]> virus;
    static int[][] posVirus;
    static int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};

    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        data = new int[n][n];
        virus = new ArrayList<>();
        posVirus = new int[m][2];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                data[i][j] = Integer.parseInt(st.nextToken());
                if(data[i][j] ==0) check++;
                if(data[i][j] ==2) virus.add(new Integer[]{i,j});
            }
        }

        res = Integer.MAX_VALUE;
        comb(0,0);
        if(res==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(res);
        br.close();
    }


    public static void comb(int cur, int cnt){
        if(cnt == m){
            search();
            return;
        }
        for(int i=cur;i<virus.size();i++){
            posVirus[cnt][0] = virus.get(i)[0];
            posVirus[cnt][1] = virus.get(i)[1];
            comb(i+1,cnt+1);
        }
    }

    public static void search(){
        int[][] d = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                d[i][j] = -1;
            }
        }
        Queue<Integer[]> que = new LinkedList<>();

        for(int i=0;i<m;i++){
            int x =posVirus[i][0];
            int y =posVirus[i][1];
            que.add(new Integer[]{x,y});
            d[x][y]=0;
        }

        int cnt=0;
        int val=0;
        while (!que.isEmpty()){
            Integer[] cur = que.poll();
            for(int i=0;i<4;i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0||ny<0||nx>=n||ny>=n||d[nx][ny]!=-1||data[nx][ny]==1) continue;
                d[nx][ny] = d[cur[0]][cur[1]] + 1;

                // 비활성 바이러스는 카운트x
                if(data[nx][ny] == 0) {
                    val = Math.max(val,d[nx][ny]);
                    cnt++;
                }
                que.add(new Integer[]{nx,ny});
            }
        }

        if(cnt == check) res = Math.min(res,val);
    }
}
