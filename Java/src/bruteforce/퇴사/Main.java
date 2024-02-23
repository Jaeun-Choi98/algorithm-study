package bruteforce.퇴사;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    bruteforce
    [problem](https://www.acmicpc.net/problem/14501)
     */
    static Integer[][] datas;
    static int n;
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        datas = new Integer[n][2];

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            datas[i][0] = Integer.parseInt(st.nextToken());
            datas[i][1] = Integer.parseInt(st.nextToken());
        }

        res = Integer.MIN_VALUE;
        search(0,0);
        bw.write(res + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int cnt, int sum){
        if(cnt==n){
            res = Math.max(res,sum);
            return;
        }

        if(datas[cnt][0] + cnt <= n){
            search(cnt+datas[cnt][0],sum+datas[cnt][1]);
        }
        search(cnt+1,sum);
    }
}
