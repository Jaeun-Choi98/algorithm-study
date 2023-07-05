package 동적계획법.타일채우기;

import java.io.*;

public class Main {

    //DP(bottom up)
    //[problem](https://www.acmicpc.net/problem/2133)

    //머리가 나빠 규칙을 찾지 못했다..
    //[해설 블로그 참조](https://nahwasa.com/entry/%EB%B0%B1%EC%A4%80-2133-%EC%9E%90%EB%B0%94-%ED%83%80%EC%9D%BC-%EC%B1%84%EC%9A%B0%EA%B8%B0-BOJ-2133-JAVA)
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        if(n%2==1){
            bw.write(0 + "\n");
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        dp = new int[n+1];
        dp[2] = 3;
        /*
        dp[4] = dp[2]*3 + 2;
        dp[6] = dp[4]*3 + 2 + dp[2]*2;
        dp[8] = dp[6]*3 + 2 + dp[2]*2 + dp[4]*2;
        */
        int buf=0;
        for(int i=4;i<=n;i+=2){
            buf += dp[i-4]*2;
            dp[i] = dp[i-2]*3 + 2 + buf;
        }
        bw.write(dp[n] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
