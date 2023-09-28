package 이분탐색.공유기설치;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
    이분 탐색
    [problem](https://www.acmicpc.net/problem/2110)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] datas = new int[n];
        int start = 0;
        int end = 0;
        for(int i=0;i<n;i++){
            datas[i] = Integer.parseInt(br.readLine());
            end = Math.max(end, datas[i]);
        }

        Arrays.sort(datas);
        //Arrays.stream(datas).forEach(s-> System.out.println(s));

        while (start<=end){
            int mid = (start+end)/2;
            int cnt = check(mid,datas);
            if(cnt<c){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        bw.write(end+"\n");
        bw.flush();
        bw.close();br.close();
    }
    public static int check(int m, int[] datas){
        int cnt=1;
        int cur = datas[0];
        for(int i=1;i<datas.length;i++){
            if(datas[i] - cur>=m){
                cur = datas[i];
                cnt++;
            }
        }
        return cnt;
    }
}
