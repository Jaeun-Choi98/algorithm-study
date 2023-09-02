package bruteforce.에너지모으기;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] datas;
    static boolean[] check;
    static long res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        datas = new int[n];
        check = new boolean[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            datas[i] = Integer.parseInt(st.nextToken());
        }
        res = Long.MIN_VALUE;

        search(0,0);
        bw.write(res + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int cnt, int sum){
        if(cnt+2 == n){
            res = Math.max(res, sum);
            return;
        }

        for(int i=1;i<n-1;i++){
            if(check[i]) continue;
            check[i] = true;
            int prev = i-1;
            int aft = i+1;
            while (true){
                if(!check[prev]) break;
                prev--;
            }
            while (true){
                if(!check[aft]) break;
                aft++;
            }

            search(cnt + 1, sum + datas[prev] * datas[aft]);
            check[i]=false;
        }
    }
}
