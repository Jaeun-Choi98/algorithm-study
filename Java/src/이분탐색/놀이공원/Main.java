package 이분탐색.놀이공원;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    이분 탐색
    [problem](https://www.acmicpc.net/problem/1561)
     */
    static long n;
    static int m;
    static int[] datas;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        datas = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) datas[i] = Integer.parseInt(st.nextToken());

        long left = 1;
        long right = (long)30*2000000000;

        if(n<=m){
            System.out.println(n);
            return;
        }

        n -= m;
        while (left<=right){
            long mid = (left+right)/2;
            long cnt = check(mid);

            if(cnt<n) left = mid + 1;
            else right = mid - 1;
        }

        long buf = check(left-1);
        n -= buf;
        int res = 0;
        for(int i=0;i<m;i++){
            if(left%datas[i]==0) n--;
            if(n==0) {
                res = i+1;
                break;
            }
        }
        System.out.println(res);

    }

    public static long check(long mid){
        long cnt = 0;
        for(int i=0;i<m;i++){
            cnt += mid/datas[i];
        }
        return cnt;
    }
}
