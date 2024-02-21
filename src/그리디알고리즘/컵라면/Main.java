package 그리디알고리즘.컵라면;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    /*
    그리디
    [problem](https://www.acmicpc.net/problem/1781)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer[]> data = new ArrayList<>();
        StringTokenizer st;

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            data.add(new Integer[]{d, val});

        }
        data.sort((o1, o2) -> o2[0]-o1[0]);
        PriorityQueue<Integer> que = new PriorityQueue<>((o1, o2) -> o2-o1);
        int maxDay = data.get(0)[0];
        int idx = 0;
        int res = 0;
        for(;maxDay>0;maxDay--){
            while (idx<n&&data.get(idx)[0] >= maxDay){
                que.add(data.get(idx)[1]);
                idx++;
            }
            if(!que.isEmpty()) res+=que.poll();

        }
        System.out.println(res);
        br.close();
    }
}
