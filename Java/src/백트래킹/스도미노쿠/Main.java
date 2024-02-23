package 백트래킹.스도미노쿠;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
    bruteforce, backtracking
    [problem](https://www.acmicpc.net/problem/4574)
     */
    static int[][] map;
    static boolean[][] check;
    static int[] dx = {0,1};
    static int[] dy = {1,0};
    static BufferedWriter bw;
    static int tc;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n;
        tc = 1;
        while(true){
            n = Integer.parseInt(br.readLine());
            if(n==0) break;
            check = new boolean[10][10];
            map = new int[9][9];
            StringTokenizer st;
            int a,b;
            int[] aIdx = new int[2];
            int[] bIdx = new int[2];
            String buf;
            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                buf = st.nextToken();
                aIdx[0] = buf.charAt(0)-'A';
                aIdx[1] = Integer.parseInt(String.valueOf(buf.charAt(1)))-1;
                b = Integer.parseInt(st.nextToken());
                buf = st.nextToken();
                bIdx[0] = buf.charAt(0)-'A';
                bIdx[1] = Integer.parseInt(String.valueOf(buf.charAt(1)))-1;

                check[a][b] = true;
                check[b][a] = true;
                map[aIdx[0]][aIdx[1]] = a;
                map[bIdx[0]][bIdx[1]] = b;
            }
            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=9;i++){
                buf = st.nextToken();
                bIdx[0] = buf.charAt(0)-'A';
                bIdx[1] = Integer.parseInt(String.valueOf(buf.charAt(1)))-1;
                map[bIdx[0]][bIdx[1]] = i;
            }

            /*for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    bw.write(map[i][j]+"");
                }
                bw.write("\n");
            }
            bw.write("\n");*/
            //System.out.println(sqr(7,2,9));
            flag =false;
            search(0);
            tc++;
        }
        bw.flush();
        bw.close();
        br.close();
    }


    public static void search(int cur) throws IOException {
        if(flag) return;
        if(cur==81){
            bw.write("Puzzle " + tc + "\n");
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    bw.write(map[i][j]+"");
                }
                bw.write("\n");
            }
            flag = true;
            return;
        }

        int x= cur/9;
        int y= cur%9;

        if(map[x][y] != 0){
            search(cur+1);
            return;
        }

        for(int i=1;i<=9;i++){
            if(!garo(x,y,i) || !sero(x,y,i) || !sqr(x,y,i)) continue;
            for(int j=0;j<2;j++){
                int nx = x + dx[j];
                int ny = y + dy[j];
                if(nx<0||ny<0||nx>=9||ny>=9||map[nx][ny]!=0) continue;
                for(int l=1;l<=9;l++){
                    if(!garo(nx,ny,l) || !sero(nx,ny,l) || !sqr(nx,ny,l)
                    || check[i][l]  || i==l) continue;
                    map[x][y] = i;
                    map[nx][ny] = l;
                    check[i][l] = true;
                    check[l][i] = true;
                    search(cur + 1);
                    map[x][y] = 0;
                    map[nx][ny] = 0;
                    check[i][l] = false;
                    check[l][i] = false;
                }
            }
        }


    }
    public static boolean garo(int x, int y, int val){
        for(int i=0;i<9;i++){
            if(map[x][i] == val) return false;
        }
        return true;
    }

    public static boolean sero(int x, int y, int val){
        for(int i=0;i<9;i++){
            if(map[i][y] == val) return false;
        }
        return true;
    }

    public static boolean sqr(int x, int y, int val){
        int sx = (x/3)*3;
        int sy = (y/3)*3;
        for(int i=sx;i<sx+3;i++){
            for(int j=sy;j<sy+3;j++){
                if(map[i][j] == val) return false;
            }
        }
        return true;
    }

}
