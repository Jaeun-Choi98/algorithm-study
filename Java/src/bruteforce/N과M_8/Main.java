package bruteforce.N과M_8;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    /*
    브루트포스
    [problem](https://www.acmicpc.net/problem/15657)
     */
    static Integer[] datas;
    static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());


        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        datas = new Integer[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            datas[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(datas);
        search(0, new ArrayList<Integer>(), 0);
        br.close();
    }

    public static void search(int cnt, ArrayList<Integer> res, int cur){
        if(cnt == m){
            res.stream().forEach(s-> System.out.print(s + " "));
            System.out.println();
            return;
        }

        for(int i=0;i<n;i++){
            if(datas[cur] > datas[i]) continue;
            res.add(datas[i]);
            search(cnt+1,res,i);
            res.remove(res.size()-1);
        }
    }
}
