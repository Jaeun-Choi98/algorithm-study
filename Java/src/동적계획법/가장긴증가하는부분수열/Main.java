package 동적계획법.가장긴증가하는부분수열;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
DP
[problem](https://www.acmicpc.net/problem/11053)

이분 탐색 알고리즘을 사용하면 N*logN의 시간복잡도를 가질 수 있다.
DP로 풀 경우, 시간 복잡도는 N^2이다.
 */

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] datas = new int[n];
        int[] d = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int result = Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            datas[i] = Integer.parseInt(st.nextToken());
            for(int j=0;j<i;j++){
                if(datas[j]<datas[i]) d[i] = Math.max(d[j] + 1, d[i]);
            }
            result = Math.max(result,d[i]);
        }

        //Arrays.stream(d).forEach(s-> System.out.println(s));
        bw.write(result+1+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
