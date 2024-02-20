package 그리디알고리즘.보석도둑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
    그리디
    [problem](https://www.acmicpc.net/problem/1202)
     */
    public static void main(String[] args) throws IOException {
        int n,k;
        List<Integer[]> jew;
        int[] bag;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        jew = new ArrayList<>();
        bag = new int[k];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            jew.add(new Integer[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
        }
        for(int i=0;i<k;i++) bag[i] = Integer.parseInt(br.readLine());

        Arrays.sort(bag);
        jew.sort(((o1, o2) -> o1[0]-o2[0]));

        PriorityQueue<Integer> que = new PriorityQueue<>((o1, o2) -> o2-o1);
        int cur = 0;
        long res = 0;
        for(int i=0;i<k;i++){
            while (cur<n && jew.get(cur)[0]<=bag[i]){
                que.add(jew.get(cur)[1]);
                cur++;
            }
            if(!que.isEmpty()) res += que.poll();
        }
        System.out.println(res);
        br.close();
    }
}
