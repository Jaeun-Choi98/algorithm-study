package 이분탐색.k번째수;

import java.io.*;

public class Main {
    /*
    이분 탐색
    [problem](https://www.acmicpc.net/problem/1300)
     */
    static long n,k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        long left = 1;
        long right = n*n;

        while(left <= right){
            long mid = (left + right)/2;
            long cnt = check(mid);
            if(cnt < k) left = mid + 1;
            else right = mid - 1;
        }

        bw.write(left + "\n");
        bw.flush();
        bw.close();br.close();
    }

    public static long check(long mid){
        long cnt = 0;
        for(int i=1;i<=n;i++){
            if(i*n<=mid) cnt += n;
            else cnt += mid/i;
        }
        return cnt;
    }
}
