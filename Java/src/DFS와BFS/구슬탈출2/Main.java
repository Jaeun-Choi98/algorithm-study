package DFS와BFS.구슬탈출2;

import java.io.*;
import java.util.StringTokenizer;

//BruteForce(DFS/BFS) + 시물레이션
//[problem](https://www.acmicpc.net/problem/13460)

//시물레이션 Tip)
//1. 경우의 수가 작기 때문에 visit배열을 만들어서 확인할 필요가 없음.
//2. 파란구슬의 이동거리와 빨간구슬의 이동거리를 비교해서 겹치는 문제를 쉽게 해결할 수 있음.
public class Main {
    static int n,m;
    static char[][] map;
    static int[] dx ={0,-1,0,1};
    static int[] dy ={-1,0,1,0};

    static int rDis,bDis;
    static int[] rCor,bCor,goal;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        rCor = new int[2];
        bCor = new int[2];
        goal = new int[2];
        //br.readLine();
        for(int i=0;i<n;i++){
            String buf = br.readLine();
            for(int j=0;j<buf.length();j++){
                map[i][j] = buf.charAt(j);
                if(map[i][j] == 'B') {
                    bCor[0] = i;
                    bCor[1] = j;
                }
                if(map[i][j] == 'R') {
                    rCor[0] = i;
                    rCor[1] = j;
                }
                if(map[i][j] == 'O') {
                    goal[0] = i;
                    goal[1] = j;
                }
            }
        }

        DFS(0,rCor,bCor);
        if(result>10) result=-1;
        bw.write(result+"\n");
        bw.flush();
        bw.close();
        br.close();

    }
    static public void DFS(int cnt, int[] rCor, int[] bCor){
        if(cnt == 10){
            return;
        }

        for(int i=0;i<4;i++){
            int rx = rCor[0];
            int ry = rCor[1];
            rDis = 0;
            boolean rflag = false;
            while(true){
                if(map[rx+dx[i]][ry+dy[i]]=='#') break;
                rx += dx[i];
                ry += dy[i];
                rDis++;
                if(rx == goal[0] && ry == goal[1]) {
                    rflag = true;
                }
            }

            int bx = bCor[0];
            int by = bCor[1];
            bDis = 0;
            boolean bflag = false;
            while(true){
                if(map[bx+dx[i]][by+dy[i]]=='#') break;
                bx += dx[i];
                by += dy[i];
                bDis++;
                if(bx == goal[0] && by == goal[1]) {
                    bflag = true;
                }
            }

            //빨간구슬만 탈출
            if(rflag && !bflag) {
                result = Math.min(cnt+1,result);
                return;
            }
            //둘다 탈출
            if(rflag && bflag) continue;
            //파란구슬만 탈출
            if(!rflag && bflag) continue;
            //파란구슬과 빨간구슬이 같은 위치라면
            if(rx==bx && ry==by){
                if(rDis < bDis){
                    bx -= dx[i];
                    by -= dy[i];
                }else{
                    rx -= dx[i];
                    ry -= dy[i];
                }
            }

            DFS(cnt+1, new int[]{rx,ry}, new int[]{bx,by});

        }
    }
}
