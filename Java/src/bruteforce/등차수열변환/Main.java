package bruteforce.등차수열변환;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
    bruteforce
    [problem](https://www.acmicpc.net/problem/17088)
     */
    static int n;
    static int[] datas;
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        if(n<=2) {
            System.out.println(0);
            return;
        }
        datas = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) datas[i] = Integer.parseInt(st.nextToken());

        res = Integer.MAX_VALUE;
        int dif;
        int f,e;
        for(int i=-1;i<2;i++){
            f = datas[0] + i;
            for(int j=-1;j<2;j++){
                e = datas[n-1] + j;
                if((e-f)%(n-1)!=0) continue;
                dif = (e-f)/(n-1);
                res = Math.min(search(f,dif),res);
            }
        }

        if(res == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(res);
        br.close();
    }

    public static int search(int f, int dif){
        int cnt = 0;
        for(int i=0;i<n;i++){
            if(Math.abs(datas[i]-(f+dif*i)) == 1) cnt++;
            else if(datas[i] == f+dif*i) continue;
            else {
                return Integer.MAX_VALUE;
            }
        }
        return cnt;
    }
}
