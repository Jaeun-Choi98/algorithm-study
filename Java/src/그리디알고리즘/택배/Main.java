package 그리디알고리즘.택배;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    /*
    그리디
    [problem](https://www.acmicpc.net/problem/8980)
    방법이 바로 떠오르지 않네..
     */
    static int n,m,cap;
    static List<Integer[]> data;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        cap = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        data = new ArrayList<>();
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            Integer[] bf = new Integer[]{Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
            data.add(bf);
        }
        data.sort(((o1, o2) -> o1[1]-o2[1]));
        int[] record = new int[n];
        int res=0;
        for(int i=0;i<m;i++){
            int s = data.get(i)[0];
            int e = data.get(i)[1];
            int val = data.get(i)[2];

            int enable = cap;
            for(int j=s;j<e;j++){
                enable = Math.min(enable,Math.min(cap - record[j], val));
            }
            for(int j=s;j<e;j++) record[j] += enable;
            res += enable;
        }
        System.out.println(res);
        br.close();
    }
}
