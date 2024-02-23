package 투포인터.귀여운라이언;

import java.io.*;
import java.util.StringTokenizer;

/*
투포인터
[problem](https://www.acmicpc.net/problem/15565)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        int[] datas;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        datas = new int[n];

        st = new StringTokenizer(br.readLine());
        int cnt = 0;
        for(int i=0;i<n;i++){
            datas[i] = Integer.parseInt(st.nextToken());
            if(datas[i] == 1) cnt++;
        }

        if(cnt<k) {
            System.out.println(-1);
            return;
        }

        cnt = 0;
        int j = 0;
        int ans = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            if(datas[i] == 1) cnt++;
            if(cnt == k){
                while(datas[j] != 1){
                    j++;
                }
                j+=1;
                ans = Math.min(ans,i-j);
                cnt--;
            }
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
