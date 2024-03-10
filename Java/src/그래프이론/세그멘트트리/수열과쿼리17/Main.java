package 그래프이론.세그멘트트리.수열과쿼리17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
    /*
    세그먼트 트리
    [problem](https://www.acmicpc.net/problem/14438)
     */
    static int[] datas, tree;
    static int size, n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        size = 4*n;
        datas = new int[n];
        tree = new int[size];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) datas[i] = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());

        init(1,0,n-1);
        int command=0, a=0, b=0;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            command = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(command==1){
                update(1,0,n-1,a-1,b);
            }else if(command==2){
                sb.append(query(1,0,n-1,a-1,b-1) + "\n");
            }
        }
        System.out.printf("%s",sb);
        br.close();
    }

    static int query(int node, int l, int r, int targetL, int targetR){
        if(r < targetL || l > targetR) return Integer.MAX_VALUE;
        if(targetL <= l && r <= targetR) return tree[node];
        int m = (l+r)/2;
        return Math.min(query(2*node,l,m,targetL,targetR), query(2*node+1,m+1,r,targetL,targetR));
    }

    static int update(int node, int l, int r, int idx, int val){
        if(l==r) return tree[node] = val;
        int m = (l+r)/2;
        if(l<=idx && idx<=m) tree[node] = Math.min(update(2*node,l,m,idx,val),tree[2*node+1]) ;
        else tree[node] = Math.min(tree[2*node], update(2*node+1, m+1, r, idx,val));
        return tree[node];
    }

    static int init(int node, int l, int r){
        if(l==r) return tree[node] = datas[l];
        int m = (l+r)/2;
        return tree[node] = Math.min(init(2*node,l,m),init(2*node+1,m+1,r));
    }
}
