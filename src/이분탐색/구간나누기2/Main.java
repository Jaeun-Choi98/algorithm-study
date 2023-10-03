package 이분탐색.구간나누기2;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    이분탐색
    [problem](https://www.acmicpc.net/problem/13397)
     */
    static int n,m;
    static int[] datas;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        datas = new int[n];
        st = new StringTokenizer(br.readLine());

        int left = 0;
        int right = -1;
        for (int i = 0; i < n; i++) {
            datas[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, datas[i]);
        }

        while(left<=right){
            int mid = (left+right)/2;
            int cnt = check(mid);
            if(cnt <= m) right = mid - 1;
            else left = mid + 1;
        }

        bw.write(left + "\n");
        bw.flush();
        bw.close();br.close();
    }

    public static int check(int m){
        int cnt = 1;
        int min = datas[0];
        int max = datas[0];

        for(int i=1;i<n;i++){
            min = Math.min(min, datas[i]);
            max = Math.max(max, datas[i]);
            if(max - min > m){
                cnt++;
                max = datas[i];
                min = datas[i];
            }
        }
        return cnt;
    }
}
