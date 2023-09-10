package DFS와BFS.연구소;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /*
    BFS, bruteforce
    [problem](https://www.acmicpc.net/problem/14502)
     */
    static int n,m,res;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static ArrayList<Integer[]> twoPos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        twoPos = new ArrayList<>();
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) twoPos.add(new Integer[]{i,j});
            }
        }

        res = Integer.MIN_VALUE;
        search(0,0);

        bw.write(res + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int star, int cnt){
        if(cnt==3){
            BFS();
            return;
        }

        for(int cur=star;cur<n*m;cur++){
            int x = cur/m;
            int y = cur%m;
            if(map[x][y] == 1 || map[x][y] == 2) continue;
            map[x][y] = 1;
            search(cur+1,cnt+1);
            map[x][y] = 0;
        }

    }

    public static void BFS(){
        Queue<Integer[]> que = new LinkedList<>();
        for(int i=0;i<twoPos.size();i++){
            que.add(twoPos.get(i));
        }

        int[][] mapCopy = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                mapCopy[i][j] = map[i][j];
            }
        }

        while (!que.isEmpty()){
            Integer[] buf = que.poll();
            for(int i=0;i<4;i++){
                int nx = buf[0] + dx[i];
                int ny = buf[1] + dy[i];
                if(nx<0||ny<0||nx>=n||ny>=m||mapCopy[nx][ny]!=0) continue;
                mapCopy[nx][ny] = 2;
                que.add(new Integer[]{nx, ny});
            }
        }

        int resBuf = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(mapCopy[i][j] == 0) resBuf++;
                //System.out.print(mapCopy[i][j]);
            }
            //System.out.println("");
        }
        res = Math.max(res,resBuf);
    }
}
