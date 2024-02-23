package DFS와BFS.Baduk2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /*
    BFS, DFS
    [problem](https://www.acmicpc.net/problem/16988)
     */
    static int n,m,res;
    static int[][] map;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = 0;
        search(0,0);
        System.out.println(res);
        br.close();
    }

    public static void search(int cur, int cnt){
        if(cnt == 2){
            check();
            return;
        }

        // cnt가 1이거나 0이면서 마지막 수에 도달한 경우
        if(cur >= n*m-1) return;

        int x,y;
        for(int i=cur;i<n*m;i++){
            x = i/m;
            y = i%m;
            if(map[x][y] == 2 || map[x][y] == 1) continue;
            map[x][y] = 1;
            search(cur+1,cnt+1);
            map[x][y] = 0;
        }

    }

    public static void check(){
        boolean[][] visited = new boolean[n][m];
        int cnt = 0;
        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if(map[i][j] == 2 && !visited[i][j]) {
                    cnt += bfs(i,j,visited);
                }
            }
        }
        res = Math.max(res,cnt);
    }

    public static int bfs(int x, int y, boolean[][] visited){
        Queue<Integer[]> que = new LinkedList<>();
        boolean flag = true;
        visited[x][y] = true;
        int cnt = 1;
        que.add(new Integer[]{x,y});
        while (!que.isEmpty()){
            Integer[] buf = que.poll();
            for(int i=0;i<4;i++){
                int nx = buf[0] + dx[i];
                int ny = buf[1] + dy[i];
                if(nx<0||ny<0||nx>=n||ny>=m||visited[nx][ny]||map[nx][ny]==1) continue;
                if(map[nx][ny] == 0) {
                    flag = false;
                    continue;
                }
                visited[nx][ny] = true;
                cnt++;
                que.add(new Integer[]{nx,ny});
            }
        }
        if(flag) return cnt;
        else return 0;
    }
}
