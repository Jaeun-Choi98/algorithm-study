package 동적계획법.트리의독립집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/2213)
     */
    static int vertices;
    static int[] w;
    static List<Integer>[] graph;

    static int[][] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        vertices = Integer.parseInt(br.readLine());
        w = new int[vertices];
        graph = new List[vertices];
        d = new int[vertices][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<vertices;i++){
            graph[i] = new ArrayList<>();
            w[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<vertices-1;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            graph[s].add(e);
            graph[e].add(s);
        }

        int[] res1 = search(0,-1);
        System.out.printf("%d\n",Math.max(res1[0],res1[1]));

        List<Integer> res2 = new ArrayList<>();
        if(d[0][0] < d[0][1]) find(res2,0,-1,true);
        else find(res2,0,-1,false);

        res2.stream().sorted().forEach(s-> System.out.printf("%d ",s));
        br.close();
    }

    static void find(List<Integer>res, int cur, int pre, boolean isContain){
        if(isContain){
            res.add(cur+1);
            for(int next: graph[cur]){
                if(next==pre) continue;
                find(res,next,cur,false);
            }
        }else{
            for(int next: graph[cur]){
                if(next==pre) continue;
                if(d[next][0] < d[next][1]) find(res,next,cur,true);
                else find(res,next,cur,false);
            }
        }
    }
    static int[] search(int cur, int pre){
        if(d[cur][0] != 0 || d[cur][1] != 0) return new int[]{d[cur][0], d[cur][1]};
        d[cur][0] = 0;
        d[cur][1] = w[cur];
        for(int next : graph[cur]){
            if(next == pre) continue;
            int[] buf = search(next,cur);
            d[cur][1] += buf[0];
            d[cur][0] += Math.max(buf[0],buf[1]);
        }
        return new int[]{d[cur][0], d[cur][1]};
    }
}
