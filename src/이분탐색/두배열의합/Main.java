package 이분탐색.두배열의합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    /*
    이진 탐색
    [problem](https://www.acmicpc.net/problem/2143)
     */
    static int T,n,m;
    static int[] A,B;
    static ArrayList<Long> Alist, Blist;
    static long res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[n];
        for(int i=0;i<n;i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        B = new int[m];
        for(int i=0;i<m;i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Alist = new ArrayList<>();
        Blist = new ArrayList<>();

        for(int i=0;i<n;i++){
            long buf = 0;
            for(int j=i;j<n;j++){
                buf += A[j];
                Alist.add(buf);
            }
        }
        for(int i=0;i<m;i++){
            long buf = 0;
            for(int j=i;j<m;j++){
                buf += B[j];
                Blist.add(buf);
            }
        }

        Alist.sort((a1,a2)->(int)((Long)a1-(Long)a2));
        Blist.sort((a1,a2)->(int)((Long)a1-(Long)a2));

        res = 0;
        long buf;
        for(int i=0;i<Alist.size();){
            buf = Alist.get(i);
            long acnt = upper(buf,Alist) - lower(buf,Alist) + 1;
            long bcnt = upper(T-buf,Blist) - lower(T-buf,Blist) + 1;
            res += acnt*bcnt;
            i+=acnt;
        }

        System.out.println(res);
        br.close();
    }

    public static long upper(long t, ArrayList<Long> list){
        int l=0;
        int r=list.size()-1;
        while(l<=r){
            int m = (l+r)/2;
            if(list.get(m) > t) r = m-1;
            else l = m + 1;
        }
        return r;
    }

    public static long lower(long t, ArrayList<Long> list){
        int l=0;
        int r=list.size()-1;
        while(l<=r){
            int m = (l+r)/2;
            if(list.get(m) < t) l = m + 1;
            else r = m - 1;
        }
        return l;
    }
}
