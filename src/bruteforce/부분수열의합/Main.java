package bruteforce.부분수열의합;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    /*
    bruteforce, subset
    [problem](https://www.acmicpc.net/problem/14225)
     */
    static int n;
    static int[] datas;
    static Set<Integer> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        datas = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            datas[i] = Integer.parseInt(st.nextToken());
        }

        set = new HashSet<>();
        search(0,0);
        int res = 0;
        while (true){
            if(!set.contains(res)) break;
            res++;
        }

        bw.write(res + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int cnt, int sum){
        set.add(sum);
        if(cnt == n){
            return;
        }
        search(cnt+1,sum+datas[cnt]);
        search(cnt+1,sum);
    }
}
