package 이분탐색.레이스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    이분탐색, 매개변수탐색
    [problem](https://www.acmicpc.net/problem/1508)
     */
    static int n,m,k;
    static int[] data;
    static int[] res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        data = new int[k];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<k;i++) data[i] = Integer.parseInt(st.nextToken());

        int l=0, r=data[k-1]-data[0];
        while (l<=r){
            int dis = (l+r)/2;
            int cnt = search(dis);
            if(cnt>=m) l = dis + 1;
            else r = dis - 1;
        }
        print(r);
        br.close();
    }
    static void print(int dis){
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        int pre = 0, cnt = 1;
        for(int i=1;i<k;i++){
            int bdis = data[i] - data[pre];
            if(bdis<dis || cnt>=m) {
                sb.append(0);
                continue;
            }
            if(cnt<m) sb.append(1);
            pre = i;
            cnt++;
        }
        //System.out.println(dis);
        System.out.println(sb);
    }

    static int search(int t){
        int cnt = 1, pre=0;
        for(int i=1;i<k;i++){
            int dis = data[i] - data[pre];
            if(dis < t) continue;
            pre = i;
            cnt++;
        }
        return cnt;
    }
}
