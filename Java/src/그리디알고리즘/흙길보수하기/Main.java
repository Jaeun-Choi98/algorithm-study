package 그리디알고리즘.흙길보수하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    /*
    그리디
    [problem](https://www.acmicpc.net/problem/1911)
     */
    static int N,L;
    static List<Integer[]> data;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        data = new ArrayList<>();
        int s=0,e=0;
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            data.add(new Integer[]{s,e});
        }

        data.sort((o1, o2) -> o1[0]-o2[0]);

        //data.stream().forEach(integers -> System.out.printf("%d %d\n",integers[0],integers[1]));
        int len=0,pos=0,res=0,cnt=0;
        for(int i=0,size=data.size();i<size;i++){
            Integer[] buf = data.get(i);
            pos = Math.max(pos,buf[0]);
            while (pos<buf[1]){
                pos += L;
                res++;
            }
        }
        System.out.println(res);
        br.close();
    }
}
