package 동적계획법.파일합치기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    dp, 누적합
    [problem](https://www.acmicpc.net/problem/11066)

    혼자서 해결하지 못하고 참고하였음.. 참고 블로그(https://nahwasa.com/entry/%EC%9E%90%EB%B0%94-%EB%B0%B1%EC%A4%80-11066-%ED%8C%8C%EC%9D%BC-%ED%95%A9%EC%B9%98%EA%B8%B0-java)
     */
    static int k;
    static int[] data;
    static int[] sum;
    static int[][] d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (tc!=0){
            k = Integer.parseInt(br.readLine());
            data = new int[k];
            sum = new int[k];
            d = new int[k][k];
            for(int i=0;i<k;i++){
                for(int j=0;j<k;j++){
                    d[i][j] = Integer.MAX_VALUE;
                }
            }
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<k;i++) {
                data[i] = Integer.parseInt(st.nextToken());
                d[i][i] = 0;
                if(i==0) sum[i] = data[i];
                else sum[i] = data[i]+sum[i-1];
            }


            for(int i=1;i<k;i++){
                for(int j=0;j<k-i;j++){
                    for(int l=0;l<i;l++){
                        d[j][j+i] = Math.min(d[j][j+i], d[j][j+l] + d[j+l+1][j+i] + (sum[j+i] - (j==0?0:sum[j-1])));
                    }
                }
            }

            System.out.println(d[0][k-1]);
            tc--;
        }
        br.close();
    }
}
