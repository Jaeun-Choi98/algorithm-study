package 분할정복.트리의순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    분할정복
    [problem](https://www.acmicpc.net/problem/2263)
     */
    static int n, cnt;
    static int[] in, post, pre;
    static int[] index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        in = new int[n]; post = new int[n]; pre = new int[n];
        index = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            in[i] = Integer.parseInt(st.nextToken());
            index[in[i]] = i;
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            post[i] = Integer.parseInt(st.nextToken());
        }
        cnt = 0;
        search(0,n-1,0,n-1);
        for(int i=0;i<n;i++){
            System.out.printf("%d ",pre[i]);
        }
        br.close();
    }

    static void search(int inL, int inR, int postL, int postR){
        if(inR < inL || postR < postL) return;
        int root = post[postR];
        pre[cnt++] = root;

        int idx = index[root];
        search(inL,idx-1,postL,postL+(idx-inL)-1);
        search(idx+1,inR,postL+(idx-inL),postR-1);
    }
}
