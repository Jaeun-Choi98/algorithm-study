package 그리디알고리즘.순회강연;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
    그리디
    [problem](https://www.acmicpc.net/problem/2109)
     */
    static int n;
    static List<Integer[]> data;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        if(n==0) {
            System.out.println(0);
            br.close();
            return;
        }
        data = new ArrayList<>();
        StringTokenizer st;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            data.add(new Integer[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
        }
        data.sort((o1, o2) -> o2[1]-o1[1]);

        int res = 0;
        PriorityQueue<Integer> que = new PriorityQueue<>(((o1, o2) -> o2-o1));
        int maxDay = data.get(0)[1];
        int cur = 0;
        while (maxDay!=0){
            for(;cur<n;cur++){
                int val = data.get(cur)[0];
                int day = data.get(cur)[1];
                if(maxDay<=day) que.add(val);
                else break;
            }
            if(!que.isEmpty()) res += que.poll();
            maxDay--;
        }
        System.out.println(res);
        br.close();
    }
}
