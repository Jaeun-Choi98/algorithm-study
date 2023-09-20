package 분할정복.별찍기2;

import java.io.*;
import java.util.*;

public class Main {
    /*
    분할정복
    [problem](https://www.acmicpc.net/problem/2448)
     */
    static int n;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new char[3*(int)Math.pow(2,10)][6*(int)Math.pow(2,10)-1];
        for(int i=0;i<n;i++){
            for(int j=0;j<2*n-1;j++){
                map[i][j] = ' ';
            }
        }
        go(n,0,n-1);
        for(int i=0;i<n;i++){
            for(int j=0;j<2*n-1;j++){
                bw.write(map[i][j]);
            }
            bw.write('\n');
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static void go(int cur, int x, int y){
        if(cur==3){
            draw(x,y);
            return;
        }
        go(cur/2,x,y);
        go(cur/2,x+cur/2,y-cur/2);
        go(cur/2,x+cur/2,y+cur/2);
    }
    public static void draw(int x, int y){
        map[x][y] = '*';
        map[x+1][y-1] = '*'; map[x+1][y+1] = '*';
        for(int i=y-2;i<=y+2;i++) map[x+2][i] = '*';
    }
}
