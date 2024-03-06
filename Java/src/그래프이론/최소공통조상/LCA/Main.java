package 그래프이론.최소공통조상.LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
    LCA
    [problem](https://www.acmicpc.net/problem/11437)
     */
    static int n, m;
    static List<Integer>[] graph;
    static int[] depth, parent;
    static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        graph = new List[n+1];
        depth = new int[n+1];
        parent = new int[n+1];
        check = new boolean[n+1];
        for(int i=1;i<n+1;i++) graph[i] = new ArrayList<>();

        for(int i=1;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            graph[parent].add(child);
            graph[child].add(parent);
        }

        Queue<Integer> que = new LinkedList<>();
        que.add(1);
        depth[1] = 1;
        check[1] = true;
        while (!que.isEmpty()){
            int pare = que.poll();
            for(int i=0, size=graph[pare].size();i<size;i++){
                int child = graph[pare].get(i);
                if(check[child]) continue;
                check[child] = true;
                depth[child] = depth[pare]+1;
                parent[child] = pare;
                que.add(child);
            }
        }

        m = Integer.parseInt(br.readLine());
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(LCA(a,b));
        }
        br.close();
    }
    static int LCA(int a, int b){
        while (depth[a] != depth[b]){
            if(depth[a] < depth[b]) b = parent[b];
            else a = parent[a];
        }
        while (a != b){
            a = parent[a];
            b = parent[b];
        }

        return a;
    }
}
