package bruteforce.단어수학;

import java.io.*;
import java.util.Arrays;

public class Main {
    /*
    bruteforce
    [problem](https://www.acmicpc.net/problem/1339)
     */
    static boolean[] enableAlpha = new boolean[26];
    static boolean[] check = new boolean[26];
    static int[] mappingAlpha = new int[26];
    static int n, count;
    static char[][] datas;
    static long[] datasCnt = new long[26];
    static long res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        datas = new char[n][];
        count = 0;

        for(int i=0;i<n;i++){
            datas[i] = br.readLine().toCharArray();
            int d = datas[i].length-1;
            for(int j=0;j<datas[i].length;j++){
                enableAlpha[datas[i][j] - 'A'] = true;
                datasCnt[datas[i][j] - 'A'] += 1*Math.pow(10,d--);
            }
        }
        for(int i=0;i<26;i++) {
            if(enableAlpha[i]) count++;
        }

        res = Long.MIN_VALUE;
        search(0);

        bw.write(res + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int cnt){
        if(cnt == 26){
            long sum = 0;
            /*for(int i=0;i<n;i++){
                long buf = 0;
                int d = 0;
                for(int j=datas[i].length-1;j>=0;j--){
                    buf += mappingAlpha[datas[i][j]-'A']*Math.pow(10,d++);
                }
                sum += buf;
            }*/
            for(int i=0;i<26;i++){
                sum += mappingAlpha[i]*datasCnt[i];
            }

            res = Math.max(res,sum);
            return;
        }

        if(!enableAlpha[cnt]) {
            mappingAlpha[cnt] = 0;
            search(cnt+1);
        }else{
            for(int i=9;i>=10-count;i--){
                if(check[i]) continue;
                check[i] = true;
                mappingAlpha[cnt] = i;
                search(cnt+1);
                check[i] = false;
            }
        }
    }
}
