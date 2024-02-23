package bruteforce.사탕게임;

import java.io.*;

public class Main {
    /*
    브루트 포스
    [problem](https://www.acmicpc.net/problem/3085)
     */
    static int n,res;
    static char[][] datas;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        datas = new char[n][n];

        for(int i=0;i<n;i++){
            String buf = br.readLine();
            for(int j=0;j<n;j++){
                datas[i][j] = buf.charAt(j);
            }
        }

        res = Integer.MIN_VALUE;
        search(0);

        bw.write(res + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int cnt){
        if(cnt == n*n){
            return;
        }
        int x = cnt/n;
        int y = cnt%n;
        char cur = datas[x][y];
        //교체하지 않고
        int buf = check(x,y,cur);
        // 상하좌우 교체
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0||ny<0||nx>=n||ny>=n) continue;
            datas[x][y] = datas[nx][ny];
            datas[nx][ny] = cur;
            buf = Math.max(buf, check(x, y, datas[x][y]));
            datas[nx][ny] = datas[x][y];
            datas[x][y] = cur;
        }
        //System.out.println(buf);
        res = Math.max(res, buf);
        search(cnt+1);
    }

    public static int check(int x, int y, char cur){
        int cntRow = 0;
        int cntCol = 0;
        //가로
        //왼쪽
        for(int i = y; i>=0; i--){
            if(datas[x][i] != cur) break;
            cntRow++;
        }
        //오른쪽
        for(int i = y+1;i<n;i++){
            if(datas[x][i] != cur) break;
            cntRow++;
        }

        //세로
        //위쪽
        for(int i = x;i>=0;i--){
            if(datas[i][y] != cur)break;
            cntCol++;
        }
        //아래쪽
        for(int i = x+1;i<n;i++){
            if(datas[i][y] != cur) break;
            cntCol++;
        }

        return Math.max(cntCol,cntRow);
    }
}
