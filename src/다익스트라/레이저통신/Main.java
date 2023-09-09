package 다익스트라.레이저통신;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    /*
    dijkstra
    [problem](https://www.acmicpc.net/problem/6087)
     */
    static int n,m,res;
    static char[][] map;
    static int[][] d;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        d = new int[n][m];

        int sx=0;
        int sy=0;
        for(int i=0;i<n;i++){
            String buf = br.readLine();
            for(int j=0;j<m;j++){
                d[i][j] = Integer.MAX_VALUE;
                map[i][j] = buf.charAt(j);
                if(map[i][j] == 'C') {
                    sx = i;
                    sy = j;
                }
            }
        }

        res = Integer.MAX_VALUE;
        search(sx,sy);

        bw.write(res+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int x, int y){
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((I1,I2)->I1[3]-I2[3]);
        //x좌표,y좌표,직전의방향,비용 순
        pq.add(new Integer[]{x, y, -1, 0});

        int cMeet = 0;

        while (!pq.isEmpty()) {

            Integer[] buf = pq.poll();

            if(d[buf[0]][buf[1]] <= buf[3]) continue;
            else d[buf[0]][buf[1]] = buf[3];

            if(map[buf[0]][buf[1]] == 'C') {
                cMeet++;
                if(cMeet == 2) {
                    res = Math.min(res,buf[3]);
                    return;
                }
            }

            for(int i=0;i<4;i++){
                int nx = buf[0] + dx[i];
                int ny = buf[1] + dy[i];
                if(nx<0||ny<0||nx>=n||ny>=m||map[nx][ny] == '*') continue;
                if(buf[2] != i && buf[2] != -1){
                    pq.add(new Integer[]{nx,ny,i,buf[3]+1});
                }else{
                    pq.add(new Integer[]{nx,ny,i,buf[3]});
                }

            }

        }
    }
}
