package 그리디알고리즘.NMK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    그리디
    [problem](https://www.acmicpc.net/problem/1201)
    정답 참고 블로그(https://hongjw1938.tistory.com/191)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n,m,k;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if(n<m+k-1 || n>m*k) {
            System.out.println(-1);
            return;
        }

        int[] startVal = new int[m];
        startVal[0] = k;

        int interval = m==1 ? 0 : (n-k)/(m-1);
        int r = m==1 ? 0 : (n-k)%(m-1);

        for(int i=1;i<m;i++){
            startVal[i] = startVal[i-1] + interval + (r > 0 ? 1 : 0);
            r--;
        }

        StringBuilder sb = new StringBuilder();
        int end = 0;
        for(int i=0;i<m;i++){
            int start = startVal[i];
            for(int j=start;j>end;j--){
                sb.append(j+" ");
            }
            end = start;
        }

        System.out.println(sb);
        br.close();
    }
}
