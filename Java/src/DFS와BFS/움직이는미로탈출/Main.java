package DFS와BFS.움직이는미로탈출;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    /*
    BFS
    [problem](https://www.acmicpc.net/problem/16954)
     */
    static char[][] map;
    static boolean[][] check;
    static int[] dx = {0, 0, -1, 1, 1, 1, -1, -1,0};
    static int[] dy = {1, -1, 0, 0, 1, -1, -1, 1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        map = new char[8][8];
        for(int i=0;i<8;i++){
            String buf = br.readLine();
            for(int j=0;j<8;j++){
                map[i][j] = buf.charAt(j);
            }
        }

        if(BFS()) bw.write(1 + "\n");
        else bw.write(0 + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean BFS(){
        if(map[7][0] == '#') return false;

        Queue<Integer[]> que = new LinkedList<>();
        que.add(new Integer[]{7, 0});

        while (!que.isEmpty()) {
            int leng = que.size();
            for(int i=0;i<leng;i++){
                Integer[] buf = que.poll();
                if(map[buf[0]][buf[1]] == '#') continue;
                for(int j=0;j<9;j++){
                    int nx = buf[0] + dx[j];
                    int ny = buf[1] + dy[j];
                    if(nx<0||ny<0||nx>=8||ny>=8||map[nx][ny]=='#') continue;
                    if(nx == 0 && ny == 7) return true;
                    que.add(new Integer[]{nx, ny});
                }
            }
            for(int l=0;l<8;l++) map[7][l] = '.';
            for(int k=6;k>=0;k--){
                for(int l=0;l<8;l++){
                    if(map[k][l] == '#'){
                        map[k+1][l] = '#';
                        map[k][l] = '.';
                    }
                }
            }

        }

        return false;
    }
}
