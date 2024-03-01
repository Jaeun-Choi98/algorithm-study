package 그래프이론.위상정렬.줄세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
    위상정렬
    [problem](https://www.acmicpc.net/problem/2252)
     */
    static int n, m;
    static List<Integer>[] grap;
    static int[] inDegree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grap = new List[n+1];
        for (int i=0;i<=n;i++) grap[i] = new ArrayList<>();
        inDegree = new int[n+1];
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            grap[from].add(to);
            inDegree[to]++;
        }
        int[] res = topologicalSort();
        for(int i=0;i<n;i++) System.out.printf("%d ",res[i]);
        br.close();
    }

    static int[] topologicalSort(){
        int[] ret = new int[n];
        int idx = 0;
        Queue<Integer> que = new LinkedList<>();
        for(int i=1;i<=n;i++){
            if(inDegree[i] == 0){
                que.add(i);
            }
        }
        while (!que.isEmpty()){
            int cur = que.poll();
            ret[idx++] = cur;
            for(int i=0, size=grap[cur].size();i<size;i++){
                int to = grap[cur].get(i);
                inDegree[to]--;
                if(inDegree[to]==0){
                    que.add(to);
                }
            }
        }
        return ret;
    }
}
