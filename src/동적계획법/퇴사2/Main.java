package 동적계획법.퇴사2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/15486)
     */
    
    static int n;
    static int[][] data;
    static int[] d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data = new int[n][2];
        d = new int[n+1];
        StringTokenizer st;
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
        }

        int cur = 0;
        int res = 0;
        for(int i=0;i<n;i++){
            cur = Math.max(cur,d[i]);
            if(i+data[i][0]<=n) {
                d[i+data[i][0]] = Math.max(cur+data[i][1],d[i+data[i][0]]);
                res = Math.max(res,d[i+data[i][0]]);
            }
        }

        //for(int i:d) System.out.println(i);
        System.out.println(res);
        br.close();

    }
}
