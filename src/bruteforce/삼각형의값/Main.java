package bruteforce.삼각형의값;

import java.io.*;
import java.util.*;


public class Main {
    /*
    bruteforce, 시물레이션
    [problem](https://www.acmicpc.net/problem/4902)
     */
    static int n;
    static long res;
    static ArrayList[] datas;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = 1;
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if(n==0) break;

            datas = new ArrayList[n];
            for(int i=0;i<n;i++){
                datas[i] = new ArrayList<Integer>();
                for(int j=0;j<2*i+1;j++) {
                    if(j==0) datas[i].add(Integer.parseInt(st.nextToken()));
                    else datas[i].add(Integer.parseInt(st.nextToken()) + (int)datas[i].get(j-1));
                }
            }
            res = Long.MIN_VALUE;
            search(0);
            System.out.print(tc+". "+res);
            System.out.println("");
            tc++;
        }

        br.close();
    }

    public static void search(int depth){
        if(depth==n) return;

        //삼각형
        for(int i=0;i<datas[depth].size();i+=2){
            int k = i;
            long sum = 0;
            for(int j=depth;j<n;j++){
                if(i==0) sum += (int)datas[j].get(k);
                else sum += (int)datas[j].get(k) - (int)datas[j].get(i-1);
                res = Math.max(sum,res);
                k+=2;
            }
        }
        //n-1-depth 부터 시작
        if(n!=1){
            for(int i=1;i<datas[n-1-depth].size();i+=2){
                int k = i-1;
                long sum = 0;
                int start = n-1-depth;
                while (i<datas[start].size() && k>=0){
                    sum += (int)datas[start].get(i) - (int)datas[start].get(k);
                    res = Math.max(sum,res);
                    k-=2;
                    start--;
                }
            }
        }
        search(depth+1);
    }
}
