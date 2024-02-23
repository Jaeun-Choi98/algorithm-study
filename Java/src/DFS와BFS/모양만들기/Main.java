package DFS와BFS.모양만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
    DFS
    [problem](https://www.acmicpc.net/problem/16932)
     */
    static int n,m;
    static int[][] map;
    static int[][] d;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[][] group;
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        d = new int[n][m];
        group = new int[n][m];

        Queue<Integer[]> que = new LinkedList<>();

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) que.add(new Integer[]{i,j});
            }
        }

        res = 0;
        // 연결 그룹의 크기를 찾기 위해 탐색.
        int groupNum = 1;
        ArrayList<Integer> groups = new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(map[i][j] == 0 || d[i][j] !=0) continue;
                d[i][j] = 1;
                group[i][j] = groupNum;
                d[i][j] = check(i,j,groupNum);
                groups.add(d[i][j]);
                groupNum++;
                res = Math.max(d[i][j],res);
            }
        }

        int i,j;
        Integer[] buf;
        while (!que.isEmpty()){
            buf = que.poll();
            i = buf[0];
            j = buf[1];
            int cnt = 1;
            HashSet<Integer> hashSet = new HashSet<>();
            for(int l=0;l<4;l++){
                int nx = i + dx[l];
                int ny = j + dy[l];
                if(nx<0||ny<0||nx>=n||ny>=m||map[nx][ny]==0) continue;
                if(hashSet.contains(group[nx][ny])) continue;
                hashSet.add(group[nx][ny]);
                cnt += groups.get(group[nx][ny]-1);
            }

            res = Math.max(res, cnt);
        }


        System.out.println(res);
        br.close();
    }

    public static int check(int x, int y, int groupNum){

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0||ny<0||nx>=n||ny>=m||map[nx][ny] == 0||d[nx][ny]!=0) continue;
            d[nx][ny] = d[x][y] + 1;
            group[nx][ny] = groupNum;
            d[x][y] = check(nx,ny,groupNum);
        }

        return d[x][y];
    }

}
