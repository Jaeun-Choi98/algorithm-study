package 동적계획법.데스노트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/2281)
     */
    static int n,m;
    static int[] data;
    static Integer[][] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = new Integer[n][m+2];
        data = new int[n];

        for(int i=0;i<n;i++) {
            data[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(search(0,0));
        br.close();
    }

    static int search(int cur, int cnt){
        if(cur==n) return 0;
        if(d[cur][cnt] != null) return d[cur][cnt];

        if(cnt + data[cur] <= m) {
            d[cur][cnt] = Math.min(search(cur+1,cnt+data[cur]+1),
                    search(cur+1,data[cur]+1) + (int)Math.pow(m-(cnt-1),2));
        }else {
            d[cur][cnt] = search(cur+1,data[cur]+1) + (int)Math.pow(m-(cnt-1),2);
        }

        return d[cur][cnt];
    }
}
