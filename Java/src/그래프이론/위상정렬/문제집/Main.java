package 그래프이론.위상정렬.문제집;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    /*
    위상정렬
    [problem](https://www.acmicpc.net/problem/1766)
     */
    static int vertices, m;
    static List<Integer>[] graph;
    static int[] inDegree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        vertices = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        inDegree = new int[vertices+1];
        graph = new List[vertices+1];
        for(int i=1;i<=vertices;i++) graph[i] = new ArrayList<>();

        int from=0,to=0;
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            inDegree[to]++;
        }

        int[] res = topological();
        for(int i=0;i<vertices;i++) System.out.printf("%d ",res[i]);
        br.close();
    }

    static int[] topological(){
        int[] ret = new int[vertices];
        int idx=0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1-o2);
        for(int i=1;i<=vertices;i++){
            if(inDegree[i]==0) pq.add(i);
        }

        while (!pq.isEmpty()){
            int cur = pq.poll();
            ret[idx++] = cur;
            for(int i=0;i<graph[cur].size();i++){
                int to = graph[cur].get(i);
                inDegree[to]--;
                if(inDegree[to] == 0){
                    pq.add(to);
                }
            }
        }

        return ret;
    }
}
