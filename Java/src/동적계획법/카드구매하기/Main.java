package 동적계획법.카드구매하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/11052)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] data = new int[n];
        int[] d = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            data[i] = Integer.parseInt(st.nextToken());
            d[i] = data[i];
        }

        for(int i=0;i<n;i++){
            for(int j=1;j<=i;j++){
                d[i] = Math.max(d[i],d[i-j]+data[j-1]);
            }
        }

        System.out.println(d[n-1]);

        br.close();
    }
}
