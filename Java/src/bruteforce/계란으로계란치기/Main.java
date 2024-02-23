package bruteforce.계란으로계란치기;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    /*
    bruteforce
    [problem](https://www.acmicpc.net/problem/16987)
    TF가 나온다면
    1. 문제를 제대로 이해했는지 확인
    2. 예외 테스트 케이스를 찾는데 주력 
     */

    static int n,res;
    static int[][] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        data = new int[n][2];

        StringTokenizer st;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
        }

        res = Integer.MIN_VALUE;
        search(0,0);

        bw.write(res+"\n");
        br.close();
        bw.flush();
        bw.close();
    }


    static void search(int cur, int sum){
        // System.out.printf("%d %d\n",cur, sum);

        if(cur==n){
            res = Math.max(res,sum);
            return;
        }

        // sum == n-1이 없다면 생기는 반례: x 0 x x 인 경우, if(cur==n)에 걸리지 않는다.
        if(data[cur][0]<=0 || sum == n-1) {
            search(cur+1, sum);
            return;
        }

        for(int i=0;i<n;i++){
            if(cur == i || data[i][0] <= 0) continue;

            int s1 = data[cur][0];
            int w1 = data[cur][1];
            int s2 = data[i][0];
            int w2 = data[i][1];


            data[cur][0] -= w2;
            data[i][0] -= w1;


            // cur(왼쪽)만 깨짐
            if(data[cur][0]<=0 && data[i][0]>0){
                search(cur+1,sum+1);
                data[cur][0] = s1;
                data[i][0] = s2;
            }// 둘다 깨짐
            else if(data[cur][0]<=0 && data[i][0]<=0){
                search(cur+1,sum+2);
                data[cur][0] = s1;
                data[i][0] = s2;
            }// 둘다 안깨짐
            else if(data[cur][0]>0 && data[i][0]>0){
                search(cur+1,sum);
                data[cur][0] = s1;
                data[i][0] = s2;
            }// i(오른쪽)만 깨짐
            else{
                search(cur+1,sum+1);
                data[cur][0] = s1;
                data[i][0] = s2;
            }
        }

    }
}
