package 이분탐색.가장긴증가하는부분수열2;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
이분탐색
[problem](https://www.acmicpc.net/problem/12015)
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] datas = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++){
            datas[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> list = new ArrayList<>();

        for(int i=0;i<n;i++){
            if(list.isEmpty()){
                list.add(datas[i]);
                continue;
            }
            if(list.get(list.size()-1)<datas[i]){
                list.add(datas[i]);
                continue;
            }
            int start = 0;
            int end = list.size()-1;
            while(start<=end){
                int mid = (start+end)/2;
                if(list.get(mid)>=datas[i]){
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }
            list.set(start,datas[i]);
        }

        bw.write(list.size()+"\n");
        bw.flush();
        bw.close();
        br.close();

    }
}
