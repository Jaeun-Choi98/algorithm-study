package 동적계획법.Acka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/12996)
     */
    static int s,a,b,c;
    static Long[][][][] d;
    static int[] da = {1,1,1,1,0,0,0},
        db = {1,1,0,0,1,0,1},
        dc = {1,0,1,0,1,1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = new Long[a+1][b+1][c+1][s+1];
        System.out.println(search(a,b,c,0));
        br.close();
    }

    static Long search(int a, int b, int c, int cnt){
        if(cnt == s){
            if(a == 0 && b == 0 && c ==0) return 1L;
            return 0L;
        }

        if(d[a][b][c][cnt] != null) return d[a][b][c][cnt];
        d[a][b][c][cnt] = 0L;

        for(int i=0;i<7;i++){
            int na = a - da[i];
            int nb = b - db[i];
            int nc = c - dc[i];
            if(na < 0 || nb < 0 || nc < 0) continue;
            d[a][b][c][cnt] += search(na,nb,nc,cnt+1);
            d[a][b][c][cnt] %= 1000000007;
        }

        return d[a][b][c][cnt];
    }
}
