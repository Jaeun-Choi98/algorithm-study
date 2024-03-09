package DFS와BFS.백조의호수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /*
    bfs
    [problem](https://www.acmicpc.net/problem/3197)
    참고블로그-https://velog.io/@woga1999/BOJ-3197%EB%B2%88-%EB%B0%B1%EC%A1%B0%EC%9D%98-%ED%98%B8%EC%88%98C
     */
    static int n,m;
    static char[][] datas;
    static boolean[][] visit;
    static int[] dx = {1,0,-1,0}, dy = {0,-1,0,1};
    static boolean isFind;

    static Queue<Integer[]> w,s,bufW,bufS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        datas = new char[n][m];
        visit = new boolean[n][m];

        w = new LinkedList<>();
        s = new LinkedList<>();
        bufW = new LinkedList<>();
        bufS = new LinkedList<>();

        int sx=0,sy=0;
        for(int i=0;i<n;i++){
            String buf = br.readLine();
            for(int j=0;j<m;j++){
                datas[i][j] = buf.charAt(j);
                if(datas[i][j] != 'X') w.add(new Integer[]{i,j});
                if(datas[i][j] == 'L') {
                    sx = i;
                    sy = j;
                }
            }
        }

        s.add(new Integer[]{sx,sy});
        visit[sx][sy] = true;
        int res = 0;
        bfsS();
        while(!isFind){
            bfsW();
            res++;
            while (!bufS.isEmpty()) s.add(bufS.poll());
            while (!bufW.isEmpty()) w.add(bufW.poll());
            bfsS();
        }
        System.out.printf("%d\n",res);
        br.close();
    }

    static void bfsW(){
        while (!w.isEmpty()){
            Integer[] curPos = w.poll();
            for(int i=0;i<4;i++){
                int nx = curPos[0] + dx[i];
                int ny = curPos[1] + dy[i];
                if(nx<0||ny<0||nx>=n||ny>=m) continue;
                if(datas[nx][ny] == 'X') {
                    datas[nx][ny] = '.';
                    bufW.add(new Integer[]{nx,ny});
                }
            }
        }
    }

    static void bfsS(){
        while (!s.isEmpty() && !isFind){
            Integer[] curPos = s.poll();
            for(int i=0;i<4;i++){
                int nx = curPos[0] + dx[i];
                int ny = curPos[1] + dy[i];
                if(nx<0||ny<0||nx>=n||ny>=m||visit[nx][ny]) continue;
                if(datas[nx][ny] == 'L') {
                    isFind = true;
                    break;
                }
                visit[nx][ny] = true;
                if(datas[nx][ny] == 'X') bufS.add(new Integer[]{nx,ny});
                else if(datas[nx][ny] == '.') s.add(new Integer[]{nx,ny});
            }
        }
    }
}
