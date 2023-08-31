package bruteforce.로또;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
    bruteforce
    [problem](https://www.acmicpc.net/problem/6603)
     */
    static int k;
    static int[] datas,res;
    //static boolean[] check;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        res = new int[6];
        while (true){
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if(k==0) break;
            datas = new int[k];
            //check = new boolean[k];
            for(int i=0;i<k;i++){
                datas[i] = Integer.parseInt(st.nextToken());
            }

            search(0,0);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    public static void search(int cnt, int cur) throws IOException {
        if(cnt == 6){
            Arrays.stream(res).forEach(s-> {
                try {
                    bw.write(s + " ");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            bw.write("\n");
            return;
        }

        for(int i=cur;i<datas.length;i++){
            //if(check[i]) continue;
            if(cnt-1>=0 && res[cnt-1] >= datas[i]) continue;
            //check[i] = true;
            res[cnt] = datas[i];
            search(cnt+1,cur+1);
            //check[i] = false;
        }
    }
}
