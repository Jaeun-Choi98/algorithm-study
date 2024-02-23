package 백트래킹.링크와스타트;

import java.io.*;
import java.util.StringTokenizer;

/*
브루트포스(백트래킹), 비트마스크
[problem](https://www.acmicpc.net/problem/15661)

스타트와링크 문제와 풀이가 같다.
마지막 cnt 조건만 다르게 해주면 된다. 시간 복잡도는 n^2*2^n 이다.
 */
public class Main {

    static int n,result;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = Integer.MAX_VALUE;
        search(0,0,0);
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int cnt, int bitMask, int check){
        if(cnt == n){
            if(check==0) return;
            int asum =0;
            int bsum =0;
            for(int i=0;i<n;i++){
                if((bitMask&(1<<i))>0){
                    for(int j=i+1;j<n;j++){
                        if((bitMask&(1<<j))>0){
                            asum+=map[i][j]+map[j][i];
                        }
                    }
                }else{
                    for(int j=i+1;j<n;j++){
                        if((bitMask&(1<<j))==0){
                            bsum+=map[i][j]+map[j][i];
                        }
                    }
                }
            }
            result = Math.min(result,Math.abs(asum-bsum));
            return;
        }

        search(cnt + 1, bitMask | (1 << cnt), check + 1);
        search(cnt + 1, bitMask, check);
    }
}
