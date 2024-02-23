package 동적계획법.팔굽혀펴기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/10564)
    dp 문제는 점화식 방법으로 풀어도 되지만, 완전 탐색에서 중복되는 부분을 메모리에 저장해서 풀 수도 있다.
    이 문제의 경우 특정 시점에 대해 탐색했는지만 확인하면 되기 때문에 boolean 타입을 메모리에 저장하지만,
    만일 득점 횟수에 대한 조건이 있었다면 정수 타입에 대한 정보를 메모리에 저장하여 풀 수도 있다.
     */
    static int N, M, res;
    static int[] data;
    static boolean[][] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (tc!=0){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            data = new int[M];
            check = new boolean[N+1][N+1];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<M;i++) data[i] = Integer.parseInt(st.nextToken());
            res = -1;
            search(0,0);
            System.out.println(res);

            tc--;
        }

        br.close();
    }

    static void search(int sum, int cur){
        if(sum == N) {
            res = Math.max(res,cur);
            return;
        }
        for(int i=0;i<M;i++){
            int newCur = cur + data[i];
            if(sum + newCur > N) continue;
            if(check[sum + newCur][newCur]) continue;
            check[sum + newCur][newCur] = true;
            search(sum+newCur,newCur);
        }
    }
}
