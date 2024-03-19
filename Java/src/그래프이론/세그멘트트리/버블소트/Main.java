package 그래프이론.세그멘트트리.버블소트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    /*
    세그먼트트리
    [problem](https://www.acmicpc.net/problem/1517)
    어렵.. 참고한 블로그(https://tussle.tistory.com/1102)
     */
    static int n;
    static List<Integer[]> datas;

    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int size = 4*n;
        tree = new long[size];
        StringTokenizer st = new StringTokenizer(br.readLine());
        datas = new ArrayList<>();
        for(int i=0;i<n;i++){
            datas.add(new Integer[]{i,Integer.parseInt(st.nextToken())});
        }
        datas.sort((o1, o2) -> o1[1]-o2[1]);
        long res = 0;
        ArrayList<Integer> equal = new ArrayList<>();
        int check = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            Integer[] buf = datas.get(i);
            if(check!=buf[1]){
                for(int idx : equal){
                    update(1,0,n-1,idx);
                }
                equal.clear();
                check = buf[1];
            }
            res += query(1,0,n-1,buf[0]+1,n-1);
            equal.add(buf[0]);
        }
        System.out.printf("%d\n",res);
        br.close();
    }

    static long query(int node, int l, int r, int tarL, int tarR){
        if(r<tarL || l > tarR) return 0;
        if(tarL<=l && r<=tarR) return tree[node];
        int m = (l+r)/2;
        return query(2*node,l,m,tarL,tarR) + query(2*node+1,m+1,r,tarL,tarR);
    }
    static long update(int node, int l, int r, int idx){
        if(l==r) return tree[node] = 1;
        int m = (l+r)/2;
        if(idx<=m) tree[node] = update(2*node,l,m,idx) + tree[2*node+1];
        else tree[node] = tree[2*node] + update(2*node+1,m+1,r,idx);
        return tree[node];
    }
}
