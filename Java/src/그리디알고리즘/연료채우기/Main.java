package 그리디알고리즘.연료채우기;

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
    [problem](https://www.acmicpc.net/problem/1826)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer[]> data = new ArrayList<>();
        StringTokenizer st;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            data.add(new Integer[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
        }
        st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        data.sort((o1, o2) -> o1[0]-o2[0]);
        PriorityQueue<Integer> que = new PriorityQueue<>((o1, o2) -> o2-o1);
        int res = 0;
        for(int i=0;i<n;i++){
            if(p>=l) break;
            Integer[] bf = data.get(i);
            while(bf[0]>p && !que.isEmpty()) {
                if(p>=l) break;
                p += que.poll();
                res++;
            }
            if(que.isEmpty() && bf[0]>p) {
                break;
            }
            que.add(bf[1]);
        }

        while (p<l && !que.isEmpty()) {
            p += que.poll();
            res++;
        }

        if(l>p) System.out.println(-1);
        else System.out.println(res);
        br.close();
    }
}