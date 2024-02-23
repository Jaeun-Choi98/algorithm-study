package 이분탐색.수이어쓰기2;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    이분탐색
    [problem](https://www.acmicpc.net/problem/1790)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int start = 1;
        int end = n;
        while (start<=end){
            int mid = (start+end)/2;
            if(count(mid)<=k) start = mid + 1;
            else end = mid - 1;
        }

        int resNum = 0;

        if(count(end) < k) resNum = start;
        else resNum = end;

        int cnt = count(resNum);

        if(count(n)<k) {
            bw.write(-1 + "\n");
        }else if(cnt == k){
            bw.write(String.valueOf(resNum).charAt(String.valueOf(resNum).length() - 1) + "\n");
        }else{
            bw.write(String.valueOf(resNum).charAt(k-count(resNum-1)-1) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int count(int m){
        int cnt = 0;
        int jaritsu = 1;
        while (m>=Math.pow(10,jaritsu)){
            cnt += ((int)Math.pow(10,jaritsu) - (int)Math.pow(10,jaritsu)/10) * jaritsu;
            jaritsu++;
        }
        cnt += jaritsu * (m - Math.pow(10, jaritsu - 1) + 1);
        return cnt;
    }
}
