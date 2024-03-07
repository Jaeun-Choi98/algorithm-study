package 그래프이론.세그멘트트리.최솟값;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
    세그멘트 트리
    [problem](https://www.acmicpc.net/problem/10868)
     */
    static int n,m,size;
    static int[] tree, datas;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        datas = new int[n];
        for(int i=0;i<n;i++) datas[i] = Integer.parseInt(br.readLine());

        size = 4*n;
        tree = new int[size];

        init(1,0,n-1);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(query(1,0,n-1,a-1,b-1));
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static int query(int node, int start, int end, int targetL, int targetR){
        if(end<targetL || start>targetR) return Integer.MAX_VALUE;
        if(targetL<=start && end<=targetR) return tree[node];
        int mid = (start+end)/2;
        return Math.min(query(2*node,start,mid,targetL,targetR),query(2*node+1,mid+1,end,targetL,targetR));
    }
    static int init(int node, int start, int end){
        if(start==end){
            return tree[node] = datas[start];
        }
        int mid = (start+end)/2;
        return tree[node] = Math.min(init(2*node,start,mid),init(2*node+1,mid+1,end));
    }
}
