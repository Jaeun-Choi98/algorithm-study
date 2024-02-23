package 분할정복.Z;

import java.io.*;
import java.util.StringTokenizer;
;

public class Main {
    /*
    분할정복
    [problem](https://www.acmicpc.net/problem/1074)
     */
    static int n;
    static long x,y;
    static long res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        x = Long.valueOf(st.nextToken());
        y = Long.valueOf(st.nextToken());

        res = 0;
        long size = (long)Math.pow(2,n);
        search(x,y,size/2);
        bw.write(res + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(long cx, long cy, long size){
        if(size==0) return;

        if(cx<size && cy<size) {
            search(cx, cy, size / 2);
        }
        else if(cx<size && cy>=size){
            res += size*size*1;
            search(cx, cy - size, size / 2);
        }
        else if(cx>=size && cy<size){
            res += size*size*2;
            search(cx - size, cy, size / 2);
        }else if(cx>=size && cy>=size){
            res += size*size*3;
            search(cx - size, cy - size, size / 2);
        }
    }
}
