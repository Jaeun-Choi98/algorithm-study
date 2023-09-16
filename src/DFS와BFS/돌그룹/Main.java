package DFS와BFS.돌그룹;

import java.io.*;
import java.util.*;
public class Main {
    /*
    BFS
    [problem](https://www.acmicpc.net/problem/12886)
    
    BFS는 완전 탐색 방법 중 하나, 넓이 우선 탐색 = 루트 노드로부터 가까운 노드 순으로 탐색 = 시작점으로 부터 최단 경로부터 탐색
     */

    static int[] datas;
    static boolean[][] check;
    static int[] g = {1,2,2};
    static int[] l = {0,0,1};

    static int[] n = {2,1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        datas = new int[3];
        for(int i=0;i<3;i++){
            datas[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(datas);
        check = new boolean[1001][1001];

        bw.write(search() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int search(){
        int flag = 0;
        Queue<Integer[]> que = new LinkedList<>();
        que.add(new Integer[]{datas[0], datas[1], datas[2]});

        while (!que.isEmpty()) {
            Integer[] buf = que.poll();
            if((int)buf[0]==(int)buf[1] && (int)buf[0]==(int)buf[2]) {
                flag = 1;
                break;
            }
            for(int i=0;i<3;i++){
                int nl = l[i];
                int ng = g[i];
                int nn = n[i];
                if((int)buf[nl]==(int)buf[ng]) continue;
                Integer[] nbuf = new Integer[]{buf[nl]+buf[nl],buf[ng]-buf[nl],buf[nn]};
                Arrays.sort(nbuf);
                if(check[nbuf[0]][nbuf[1]]) continue;
                check[nbuf[0]][nbuf[1]]=true;
                que.add(nbuf);
            }

        }

        return flag;
    }
}
