package 이분탐색.나무자르기;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    이분 탐색
    [problem](https://www.acmicpc.net/problem/2805)
     */
    static int n;
    static int[] data;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = 0;
        int end = 0;
        data = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            data[i] = Integer.parseInt(st.nextToken());
            end = Math.max(end, data[i]);
        }

        long sum = 0;
        while (start<=end){
            int mid = (start+end)/2;
            sum = check(mid);
            if(sum <= m){
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }

        if(check(start)>=m){
            bw.write(start+"\n");
        }else{
            bw.write(end+ "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
    public static long check(int m){
        long s = 0;
        for(int i=0;i<n;i++){
            if(data[i] - m < 0) continue;
            s += data[i] - m;
        }
        return s;
    }
}
