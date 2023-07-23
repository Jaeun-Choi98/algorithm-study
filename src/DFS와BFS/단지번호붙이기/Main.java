package DFS와BFS.단지번호붙이기;

import java.io.*;
import java.util.ArrayList;

public class Main {
    static int n;
    static int[][] map;
    static boolean[][] check;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static ArrayList<Integer> result;
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        check = new boolean[n][n];
        result = new ArrayList<>();

        for(int i=0;i<n;i++){
            String str = br.readLine();
            for(int j=0;j<str.length();j++){
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j] == 0) continue;
                if(check[i][j]) continue;
                check[i][j] = true;
                res = 0;
                dfs(i, j);
                result.add(res);
            }
        }

        System.out.println(result.size());
        result.stream().sorted().forEach(s-> System.out.println(s));

        br.close();
    }

    public static void dfs(int x,int y){
        res++;
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0||ny<0||nx>=n||ny>=n) continue;
            if(map[nx][ny] ==0 || check[nx][ny]) continue;
            check[nx][ny] = true;
            dfs(nx,ny);
        }
    }
}
