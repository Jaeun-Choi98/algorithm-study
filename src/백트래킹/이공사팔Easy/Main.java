package 백트래킹.이공사팔Easy;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    bruteforce, simulation
    [problem](https://www.acmicpc.net/problem/12100)
     */
    static int n;
    static int[][] map;
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        StringTokenizer st;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //left();
        //right();
        //up();
        //down();
        /*for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(map[i][j]);
            }
            System.out.println("");
        }*/
        res = 0;
        search(0);
        bw.write(res+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int cnt){
        if(cnt==5){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    res = Math.max(res,map[i][j]);
                }
            }
            return;
        }
        int[][] buf = new int[n][n];
        move(map,buf);

        left();
        search(cnt+1);
        move(buf,map);

        right();
        search(cnt+1);
        move(buf,map);

        up();
        search(cnt+1);
        move(buf,map);

        down();
        search(cnt+1);
        move(buf,map);

    }
    public static void move(int[][] buf1,int[][] buf2){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                buf2[i][j] = buf1[i][j];
            }
        }
    }

    public static void left(){
        for(int i=0;i<n;i++) {
            for (int j = 0; j < n - 1; j++) {
                if(map[i][j] == 0){
                    int idx = j+1;
                    while(map[i][idx]==0){
                        idx++;
                        if(idx==n) {
                            idx=n-1;
                            break;
                        }
                    }
                    map[i][j] = map[i][idx];
                    map[i][idx] = 0;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n-1;j++){
                if(map[i][j] == map[i][j+1]) {
                    map[i][j] *= 2;
                    map[i][j+1] = 0;
                    j++;
                }
            }
        }
        for(int i=0;i<n;i++) {
            for (int j = 0; j < n - 1; j++) {
                if(map[i][j] == 0){
                    int idx = j+1;
                    while(map[i][idx]==0){
                        idx++;
                        if(idx==n) {
                            idx=n-1;
                            break;
                        }
                    }
                    map[i][j] = map[i][idx];
                    map[i][idx] = 0;
                }
            }
        }
    }

    public static void right(){
        for(int i=0;i<n;i++){
            for(int j=n-1;j>0;j--){
                if(map[i][j] == 0){
                    int idx = j-1;
                    while(map[i][idx]==0){
                        idx--;
                        if(idx==-1){
                            idx=0;
                            break;
                        }
                    }
                    map[i][j] = map[i][idx];
                    map[i][idx] = 0;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=n-1;j>0;j--){
                if(map[i][j] == map[i][j-1]){
                    //System.out.println(map[i][j]);
                    map[i][j] *= 2;
                    map[i][j-1] = 0;
                    j--;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=n-1;j>0;j--){
                if(map[i][j] == 0){
                    int idx = j-1;
                    while(map[i][idx]==0){
                        idx--;
                        if(idx==-1){
                            idx=0;
                            break;
                        }
                    }
                    map[i][j] = map[i][idx];
                    map[i][idx] = 0;
                }
            }
        }
    }

    public static void up() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (map[j][i] == 0) {
                    int idx = j+1;
                    while(map[idx][i]==0){
                        idx++;
                        if(idx==n) {
                            idx=n-1;
                            break;
                        }
                    }
                    map[j][i] = map[idx][i];
                    map[idx][i] = 0;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (map[j][i] == map[j + 1][i]) {
                    map[j][i] *= 2;
                    map[j + 1][i] = 0;
                    j++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (map[j][i] == 0) {
                    int idx = j+1;
                    while(map[idx][i]==0){
                        idx++;
                        if(idx==n) {
                            idx=n-1;
                            break;
                        }
                    }
                    map[j][i] = map[idx][i];
                    map[idx][i] = 0;
                }
            }
        }
    }
    public static void down(){
        for(int i=0;i<n;i++) {
            for (int j = n - 1; j > 0; j--) {
                if(map[j][i] == 0) {
                    int idx = j-1;
                    while(map[idx][i]==0){
                        idx--;
                        if(idx==-1){
                            idx=0;
                            break;
                        }
                    }
                    map[j][i] = map[idx][i];
                    map[idx][i] = 0;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=n-1;j>0;j--){
                if(map[j][i] == map[j-1][i]) {
                    map[j][i] *= 2;
                    map[j-1][i] = 0;
                    j--;
                }
            }
        }
        for(int i=0;i<n;i++) {
            for (int j = n - 1; j > 0; j--) {
                if(map[j][i] == 0) {
                    int idx = j-1;
                    while(map[idx][i]==0){
                        idx--;
                        if(idx==-1){
                            idx=0;
                            break;
                        }
                    }
                    map[j][i] = map[idx][i];
                    map[idx][i] = 0;
                }
            }
        }
    }
}
