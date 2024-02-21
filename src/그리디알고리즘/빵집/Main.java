package 그리디알고리즘.빵집;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    그리디
    [problem](https://www.acmicpc.net/user/chlwodns98)
     */
    static int r,c;
    static char[][] map;
    static boolean[][] check;
    static boolean flag;
    static int[] dx = {-1,0,1}, dy = {1,1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        check = new boolean[r][c];
        for(int i=0;i<r;i++) map[i] = br.readLine().toCharArray();
        for(int i=0;i<r;i++) {
            flag = false;
            search(i,0);
        }
        int res =0;
        for(int i=0;i<r;i++) {
            if(check[i][c-1]) res++;
        }
        /*for(int i=0;i<r;i++){
            for(int j=0;j<c;j++) System.out.print(check[i][j]);
            System.out.println("");
        }*/
        System.out.println(res);
        br.close();
    }

    static void search(int x, int y){
        if(y==c-1) {
            flag = true;
            return;
        }
        for(int i=0;i<3;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0 || ny <0 || nx>=r || ny>=c || check[nx][ny] || map[nx][ny] == 'x' || flag) continue;
            check[nx][ny] = true;
            search(nx,ny);
        }
    }
}
