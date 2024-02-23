package 이분탐색.중량제한;

import java.io.*;
import java.net.Inet4Address;
import java.util.*;

public class Main {
    /*
    binary search, DFS
    [problem](https://www.acmicpc.net/status?user_id=chlwodns98&problem_id=1939&from_mine=1)
     */
    static int n,m,a,b;
    static List<Integer[]>[] list;
    static boolean[] visited;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n];
        for(int i=0;i<n;i++) list[i] = new ArrayList<>();

        int start = 0;
        int end = 0;

        // 0번째 인덱스 = 도착지점, 1번째 인덱스 = 간선의 value
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken());
            list[s].add(new Integer[]{e, v});
            list[e].add(new Integer[]{s, v});
            end = Math.max(end,v);
        }

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken()) - 1;
        b = Integer.parseInt(st.nextToken()) - 1;

        while (start<=end){
            int mid = (start+end)/2;
            flag = false;
            visited = new boolean[n];
            check(a, mid);
            if(flag){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        bw.write(end + "\n");
        bw.flush();
        bw.close();br.close();
    }


    public static void check(int cur, int m){
        visited[cur] = true;
        if(cur == b) {
            flag = true;
            return;
        }
        for(int i=0;i<list[cur].size();i++){
            int e = list[cur].get(i)[0];
            int v = list[cur].get(i)[1];
            if(visited[e]) continue;
            if(v<m) continue;
            check(e,m);
        }
    }
}
