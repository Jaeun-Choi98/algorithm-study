package 동적계획법.뮤탈리스크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/12869)
     */

    static int[] da = {9,9,3,1,1,3};
    static int[] db = {3,1,9,9,3,1};
    static int[] dc = {1,3,1,3,9,9};


    static int[][][] d;
    static int[] data;
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        data = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            data[i] = Integer.parseInt(st.nextToken());
        }

        d = new int[100][100][100];
        res = Integer.MAX_VALUE;
        search(0,0,0,0);
        System.out.println(res);
        br.close();
    }

    static void search(int a, int b, int c, int cnt){
        if(a>=data[0] && b>=data[1] && c>=data[2]){
            res = Math.min(res,cnt);
            return;
        }

        if(d[a][b][c] != 0 && d[a][b][c] <= cnt) {
            return;
        }
        //System.out.printf("%d %d %d\n",a,b,c);
        d[a][b][c] = cnt;

        for(int i=0;i<6;i++){
            int na = a >= data[0] ? a : a + da[i];
            int nb = b >= data[1] ? b : b + db[i];
            int nc = c >= data[2] ? c : c + dc[i];
            search(na,nb,nc,cnt+1);
        }

    }
}
