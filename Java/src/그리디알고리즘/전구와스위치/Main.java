package 그리디알고리즘.전구와스위치;

import java.io.*;

public class Main {
    /*
    Greedy
    [problem](https://www.acmicpc.net/problem/2138)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] init = new int[n];
        int[] want = new int[n];

        for(int i=0;i<2;i++){
            String buf = br.readLine();
            for(int j=0;j<n;j++){
                if(i==0) init[j] = buf.charAt(j) - '0';
                else want[j] = buf.charAt(j) - '0';
            }
        }



        int res = Integer.MAX_VALUE;
        int c = 2;
        while (c-- != 0){
            int cnt = 0;
            int[] cur = new int[n];
            for(int i=0;i<n;i++) cur[i] = init[i];
            if(c==0) {
                cur[0] ^= 1;
                cur[1] ^= 1;
                cnt++;
            }

            boolean flag = check(n,cur,want);
            for(int i=1;i<n;i++){
                if(flag) break;
                if(cur[i-1] != want[i-1]) {
                    cur[i-1] ^= 1;
                    cur[i] ^= 1;
                    if(i+1<n) {cur[i+1] ^= 1;}
                    cnt++;
                    flag = check(n,cur,want);
                }
                //flag = check(n,cur,want); 여기에 적게 되면 시간초과가 난다.
                //if문 안에 적은 이유는 변경이 있을 때만 체크하면 되기 때문..
            }

            if(flag) res = Math.min(res, cnt);
        }

        if(res == Integer.MAX_VALUE) res = -1;
        bw.write(res + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean check(int n, int[] cur, int[] want){
        boolean flag = true;
        for(int j=0;j<n;j++){
            if(!flag) break;
            if(cur[j] != want[j]) flag = false;
        }
        return flag;
    }
}
