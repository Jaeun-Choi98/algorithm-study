package DFS와BFS.물통;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /*
    BFS
    [problem](https://www.acmicpc.net/problem/2251)
     */
    static boolean[][] check;
    static int a,b,c;
    static boolean[] res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        check = new boolean[b+1][c+1];
        res = new boolean[c+1];
        search();
        for(int i=0;i<c+1;i++) {
            if(res[i]) System.out.printf(i + " ");
        }

        br.close();
    }

    public static void search(){
        Queue<Integer[]> que = new LinkedList<>();
        que.add(new Integer[]{0,0,c});
        while (!que.isEmpty()){
            Integer[] buf = que.poll();
            int difA = a - buf[0];
            int difB = b - buf[1];
            int difC = c - buf[2];
            if(check[buf[1]][buf[2]]) continue;
            check[buf[1]][buf[2]] = true;
            if(buf[0] == 0) res[buf[2]] = true;
            // c->b
            if(difB > buf[2]) que.add(new Integer[]{buf[0],buf[1]+buf[2],0});
            else que.add(new Integer[]{buf[0],b,buf[2]-difB});
            // c->a
            if(difA > buf[2]) que.add(new Integer[]{buf[0]+buf[2],buf[1],0});
            else que.add(new Integer[]{a,buf[1],buf[2]-difA});
            // b->c
            if(difC > buf[1]) que.add(new Integer[]{buf[0],0,buf[2]+buf[1]});
            else que.add(new Integer[]{buf[0],buf[1]-difC,c});
            // a->c
            if(difC > buf[0]) que.add(new Integer[]{0,buf[1],buf[2]+buf[0]});
            else que.add(new Integer[]{buf[0]-difC,buf[1],c});

            // a->b
            if(difB > buf[0]) que.add(new Integer[]{0,buf[1]+buf[0],buf[2]});
            else que.add(new Integer[]{buf[0]-difB,b,buf[2]});
            // b->a
            if(difA >buf[1]) que.add(new Integer[]{buf[0]+buf[1],0,buf[2]});
            else que.add(new Integer[]{a,buf[1]-difA,buf[2]});
        }
    }
}
