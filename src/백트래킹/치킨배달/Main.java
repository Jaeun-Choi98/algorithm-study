package 백트래킹.치킨배달;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    /*
    bruteforce, Comb
    [problem](https://www.acmicpc.net/problem/15686)
     */
    static int n,m,res;
    static int map[][];
    static boolean check[];
    static ArrayList<Integer[]> home,store;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        home = new ArrayList<>();
        store = new ArrayList<>();

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) home.add(new Integer[]{i,j});
                if(map[i][j] == 2) store.add(new Integer[]{i,j});
            }
        }
        check = new boolean[store.size()];
        res = Integer.MAX_VALUE;
        search(0,0);
        System.out.println(res);
        br.close();
    }

    public static void search(int cur, int cnt){
        if(cnt==m){
            int sum = cal();
            res = Math.min(res, sum);
            return;
        }

        // comb
        for(int i=cur;i<store.size();i++){
            check[i] = true;
            search(i+1,cnt+1);
            check[i] = false;
        }
    }

    public static int cal(){
        int sum = 0;
        for(int i=0;i<home.size();i++){
            Integer[] buf = home.get(i);
            int bufSum = Integer.MAX_VALUE;
            for(int j=0;j<store.size();j++){
                if(check[j])
                    bufSum = Math.min(bufSum,Math.abs(buf[0]-store.get(j)[0]) + Math.abs(buf[1]-store.get(j)[1]));
            }
            sum += bufSum;
        }

        return sum;
    }
}
