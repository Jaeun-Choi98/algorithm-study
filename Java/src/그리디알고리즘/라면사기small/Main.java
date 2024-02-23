package 그리디알고리즘.라면사기small;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    그리디
    [problem](https://www.acmicpc.net/problem/18185)
    참고블로그(https://david0506.tistory.com/47)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] data = new int[n+3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            data[i] = Integer.parseInt(st.nextToken());
        }

        int res = 0;
        for(int i=1;i<=n;i++){
            int cnt = Integer.MAX_VALUE;
            if(data[i+1]>data[i+2]){
                cnt = Math.min(data[i], data[i+1]-data[i+2]);
                res += cnt*5;
                data[i] -= cnt;
                data[i+1] -= cnt;

                cnt = Math.min(data[i],data[i+1]);
                cnt = Math.min(cnt,data[i+2]);
                res += 7*cnt;
                data[i] -= cnt;
                data[i+1] -= cnt;
                data[i+2] -= cnt;
            }else{
                cnt = Math.min(data[i],data[i+1]);
                cnt = Math.min(cnt,data[i+2]);
                res += 7*cnt;
                data[i] -= cnt;
                data[i+1] -= cnt;
                data[i+2] -= cnt;

                cnt = Math.min(data[i], data[i+1]);
                res += cnt*5;
                data[i] -= cnt;
                data[i+1] -= cnt;
            }

            res += 3*data[i];
            data[i] = 0;
        }
        System.out.println(res);
        br.close();
    }
}
