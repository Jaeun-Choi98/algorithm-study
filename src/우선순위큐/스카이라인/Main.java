package 우선순위큐.스카이라인;

import java.io.*;
import java.util.*;

public class Main {
    /*
    우선순위 큐
    [problem](https://www.acmicpc.net/problem/1933)

    머리가 나빠.. 참고하였음 -> https://gre-eny.tistory.com/293
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        //x좌표순으로 오름차순 정렬, x좌표가 같다면(하나의 빌딩이라면) 높이에 따라 내림차순
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((p1, p2) -> {
            if(p1[0] == p2[0]) {
               return p2[1]-p1[1];
            }else return p1[0] - p2[0];
        });

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            pq.add(new Integer[]{x1, h});
            //끝 점이라는 것을 알려주기 위해서 x2와 -h를 넣음
            pq.add(new Integer[]{x2, -h});
        }

        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        int curX =0;
        int curH =0;
        //마지막 빌딩의 끝점을 표시하기 위해
        map.put(0,1);
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Integer[] buf = pq.poll();
            int h = buf[1];
            int x = buf[0];
            if(h>0){
                map.put(h,map.getOrDefault(h,0)+1);
            }else{
                if(map.get(-h) == 1) map.remove(-h);
                else map.replace(-h, map.get(-h) -1);
            }

            int bufH = map.firstKey();
            if(curX != x && curH != bufH){
                sb.append(x + " " + bufH + " ");
                curX = x;
                curH = bufH;
            }
        }
        bw.write(sb.toString().trim());
        bw.flush();
        bw.close();
        br.close();
    }
}
