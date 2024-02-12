package 동적계획법.소형기관차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/2616)
     */
    static int n, cap;
    static int[] data;
    static int[] sum;
    static Integer[][] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data = new int[n+1];
        sum = new int[n+1];
        d = new Integer[4][n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        cap = Integer.parseInt(br.readLine());

        sum[1] = data[1];
        for(int i=2;i<=n;i++){
            sum[i] = sum[i-1] + data[i];
        }

        System.out.println(search(3, n));
        br.close();
    }

    static int search(int cnt, int idx){
        if(cnt == 0 || idx <=0) return 0;
        if(d[cnt][idx] != null) return d[cnt][idx];
        d[cnt][idx] = 0;
        if(idx - cap >= 0){
            d[cnt][idx] = Math.max(search(cnt,idx-1), search(cnt-1,idx-cap) + sum[idx]-sum[idx-cap]);
        }

        return d[cnt][idx];
    }
}
