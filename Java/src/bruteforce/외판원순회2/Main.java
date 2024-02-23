package bruteforce.외판원순회2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
    bruteforce, 그래프 순회(DFS)
    [probelm](https://www.acmicpc.net/problem/10971)
     */
    static int n,res;
    static ArrayList<Integer>[] grap;
    static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        grap = new ArrayList[n];
        check = new boolean[n];
        for(int i=0;i<n;i++){
            grap[i] = new ArrayList<Integer>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                grap[i].add(j, Integer.parseInt(st.nextToken()));
            }
        }

        //Arrays.stream(grap).forEach(list->list.stream().forEach(s-> System.out.println(s)));
        res = Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            check[i] = true;
            search(i, i,0,0);
            check[i] = false;
        }

        bw.write(res+" ");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int start, int cur, int cnt, int sum){
        if(cnt == n-1){
            if(grap[cur].get(start) != 0) res = Math.min(res, sum+grap[cur].get(start));
        }

        for(int i=0;i<grap[cur].size();i++){
            if(check[i] || grap[cur].get(i) == 0) continue;
            check[i] = true;
            search(start, i,cnt+1,sum + grap[cur].get(i));
            check[i] = false;
        }
    }
}
