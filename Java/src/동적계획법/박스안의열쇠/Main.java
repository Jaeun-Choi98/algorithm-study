package 동적계획법.박스안의열쇠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/1413)
     */
    static int n,m;
    static Long[][] d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        d = new Long[n+1][n+1];

        Long S = search(n,n);
        Long A = search(n,m);
        Long m = gcd(S,A);
        System.out.printf("%d/%d\n",A/m,S/m);
        br.close();
    }

    static Long search(int n, int m){
        if(n==0) return 1L;
        if(m==0) return 0L;
        if(d[n][m] != null) return d[n][m];
        d[n][m] = 0L;
        d[n][m] = (n-1)*search(n-1,m) + search(n-1,m-1);
        return d[n][m];
    }

    static Long gcd(Long a, Long b){
        return b == 0 ? a : gcd(b,a%b);
    }
}
