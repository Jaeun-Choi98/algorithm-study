package 동적계획법.외판원순회;
import java.util.*;
import java.io.*;


/*
문제 URL:https://www.acmicpc.net/problem/2098
머리가 나빠 혼자서 못풀었습니다..
https://mygumi.tistory.com/361 블로그를 참고하였습니다.
 */

public class Main {
    static int[][] grap;
    static int[][] dp;
    static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        grap = new int[N][N];
        dp = new int[N][1<<N];

        // 인풋 데이터 값 초기화, 방향그래프를 인접 행렬으로 표현(인접 리스트로 표현해도 됨)
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                grap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = dynamicProgramingUsingBitMask(0,1);
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int dynamicProgramingUsingBitMask(int cur, int binary){
        if(binary == (1<<N) - 1){
            if(grap[cur][0] == 0){
                //Integer.MAX_VALUE에서 추가적으로 양수의 값이 더해지면 오버플로우가 발생해서 음수가 될 수 있어서 적당히 큰값을 빼줌.
                return Integer.MAX_VALUE - 1000000;
            }else{
                return grap[cur][0];
            }
        }

        //동적 프로그래밍에 중요한 부분
        //ex) dp[1][00111]이 이미있다면 탐색하지 않는다. 이때 dp[1][00111]은 이후의 3->4->0, 4->3->0중 최소값을 담고 있음
        if(dp[cur][binary] != 0){
            return dp[cur][binary];
        }

        dp[cur][binary] = Integer.MAX_VALUE - 1000000;
        for(int i=0;i<N;i++){
            int next = 1 << i;
            if(grap[cur][i] == 0 || (binary & next) > 0) continue;
            dp[cur][binary] = Math.min(dp[cur][binary],dynamicProgramingUsingBitMask(i,next|binary) + grap[cur][i]);
        }
        return dp[cur][binary];
    }
}
