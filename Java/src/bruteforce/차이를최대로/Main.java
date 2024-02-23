package bruteforce.차이를최대로;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    bruteforce, bitmask
    [problem](https://www.acmicpc.net/problem/10819)
     */
    static int n,res;
    static int[] datas, comb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        datas = new int[n];
        comb = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            datas[i] = Integer.parseInt(st.nextToken());
        }

        res = Integer.MIN_VALUE;
        search(0,0);


        bw.write(res + " ");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int cnt, int bit){
        if(bit == (1<<n) - 1){
            res = Math.max(calc(),res);
        }
        for(int i=0;i<n;i++){
            if((bit & 1<<i) >0) continue;
            comb[cnt] = datas[i];
            search(cnt+1,bit|1<<i);
        }
    }

    public static int calc(){
        int sum = 0;
        for(int i=0;i<n-1;i++){
            sum += Math.abs(comb[i] - comb[i + 1]);
        }
        return sum;
    }
}
