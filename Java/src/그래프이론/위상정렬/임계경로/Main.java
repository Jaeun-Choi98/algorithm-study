package 그래프이론.위상정렬.임계경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
    위상정렬, 임계경로
    [problem](https://www.acmicpc.net/problem/1948)
     */
    static int vertices, n, start, dest;
    static int[] d, rd;
    static List<Integer[]>[] graph, rgraph;
    static int[] inDegree, rinDegree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        vertices = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        rgraph = new List[vertices+1];
        graph = new List[vertices+1];
        rinDegree = new int[vertices+1];
        inDegree = new int[vertices+1];
        for(int i=1;i<=vertices;i++) {
            graph[i] = new ArrayList<>();
            rgraph[i] = new ArrayList<>();
        }

        int from=0,to=0,weight=0;
        StringTokenizer st;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            graph[from].add(new Integer[]{to,weight});
            inDegree[to]++;

            rgraph[to].add(new Integer[]{from,weight});
            rinDegree[from]++;
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());

        d = new int[vertices+1];
        rd = new int[vertices+1];
        topological(start);

        System.out.println(d[dest]);
        System.out.println(rtopological(dest));

        br.close();
    }

    static int rtopological(int s){
        int res = 0;
        Queue<Integer> que = new LinkedList<>();
        que.add(s);
        while (!que.isEmpty()){
            int cur = que.poll();
            for(int i=0, size=rgraph[cur].size();i<size;i++){
                int next = rgraph[cur].get(i)[0];
                int w = rgraph[cur].get(i)[1];
                rinDegree[next]--;
                if(w + rd[cur] + d[next] == d[dest]) res++;
                if(rd[next] < rd[cur] + w){
                    rd[next] = rd[cur] + w;
                }
                if(rinDegree[next]==0) que.add(next);
            }
        }
        return res;
    }

    static void topological(int s){
        Queue<Integer> que = new LinkedList<>();
        que.add(s);
        while (!que.isEmpty()){
            int cur = que.poll();
            for(int i=0, size=graph[cur].size();i<size;i++){
                int next = graph[cur].get(i)[0];
                int w = graph[cur].get(i)[1];
                inDegree[next]--;
                if(d[next] < d[cur] + w){
                    d[next] = d[cur] + w;
                }
                if(inDegree[next]==0) que.add(next);
            }
        }
    }
}
