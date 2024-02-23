package DFS와BFS.적록색약;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    /*
    BFS
    [problem](https://www.acmicpc.net/problem/10026)
     */
    static int n;
    static char[][] mapA;
    static char[][] mapB;

    static boolean[][] visited;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        mapA = new char[n][n];
        mapB = new char[n][n];
        for(int i=0;i<n;i++){
            String buf = br.readLine();
            for(int j=0;j<n;j++){
                mapA[i][j] = buf.charAt(j);
                if(buf.charAt(j)=='G') mapB[i][j] = 'R';
                else mapB[i][j] = buf.charAt(j);
            }
        }
        visited = new boolean[n][n];
        int resA = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(visited[i][j]) continue;
                visited[i][j] = true;
                resA++;
                search(i,j,mapA);
            }
        }

        visited = new boolean[n][n];
        int resB = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(visited[i][j]) continue;
                visited[i][j] = true;
                resB++;
                search(i,j,mapB);
            }
        }

        bw.write(resA+" "+resB);
        bw.flush();
        bw.close();
        br.close();

    }

    public static void search(int x, int y, char[][] map){
        Queue<Integer[]> que = new LinkedList<>();
        que.add(new Integer[]{x,y});

        while (!que.isEmpty()) {
            Integer[] buf = que.poll();
            for(int i=0;i<4;i++){
                int nx = buf[0] + dx[i];
                int ny = buf[1] + dy[i];
                if(nx<0||ny<0||nx>=n||ny>=n||visited[nx][ny]||map[buf[0]][buf[1]] != map[nx][ny]) continue;
                visited[nx][ny] = true;
                que.add(new Integer[]{nx, ny});
            }
        }

    }
}
