package 백트래킹.사다리조작;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    구현, bruteforce
    [problem](https://www.acmicpc.net/problem/15684)
     */
    static int row, col, m;
    static boolean[][][] datas;
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        // [][][0] -> left, [][][1] -> right
        datas = new boolean[row][col][2];
        res = 4;

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            datas[a][b][1] = true;
            datas[a][b+1][0] = true;
        }

        search(0,0,0,0);
        if(res>3) System.out.println(-1);
        else System.out.println(res);
        br.close();
    }

    public static void search(int x, int y, int Y, int cnt){
        if(cnt>3) return;
        if(Y==col){
            res = Math.min(res,cnt);
            return;
        }

        if(x==row) {
            if(y!=Y) return;
            search(0,Y+1, Y+1, cnt);
        }else if(datas[x][y][0]){
            search(x+1,y-1,Y,cnt);
        }else if(datas[x][y][1]){
            search(x+1,y+1,Y,cnt);
        }else{
            // 왼쪽
            if(y-1>=0) {
                datas[x][y][0] = true;
                datas[x][y-1][1] = true;
                search(x+1,y-1,Y,cnt+1);
                datas[x][y][0] = false;
                datas[x][y-1][1] = false;
            }
            // 중간
            search(x+1,y,Y,cnt);
            // 오른쪽
            if(y+1<col){
                datas[x][y][1] = true;
                datas[x][y+1][0] = true;
                search(x+1,y+1,Y,cnt+1);
                datas[x][y][1] = false;
                datas[x][y+1][0] = false;
            }
        }

    }
}
