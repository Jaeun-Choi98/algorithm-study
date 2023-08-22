package bruteforce.리모컨;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    /*
    bruteforce
    [problem](https://www.acmicpc.net/problem/1107)
     */
    static int res;
    static ArrayList<Integer> datas;
    static int start = 100;
    static int target;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        target = Integer.parseInt(br.readLine());
        int brokenCnt = Integer.parseInt(br.readLine());

        datas = new ArrayList<>();
        for(int i=0;i<10;i++) datas.add(i);
        if(brokenCnt!=0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<brokenCnt;i++){
                int tmp = Integer.parseInt(st.nextToken());
                for(int j=0;j<datas.size();j++){
                    if (datas.get(j) == tmp ) {
                        datas.remove(j);
                    }
                }
            }
        }

        //datas.stream().forEach(s -> System.out.println(s));
        res = Math.abs(target-start);
        search(0,0);
        bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    public static void search(int cnt, int cur){
        if(cnt != 0){
            res = Math.min(res,cnt+Math.abs(target-cur));
        }
        //System.out.print(cnt + " ");
        //System.out.println(cur);
        cur = cur*10;

        if(cnt == 6){
            return;
        }

        for(int i=0;i<datas.size();i++){
            search(cnt+1,cur+datas.get(i));
        }
    }
}
