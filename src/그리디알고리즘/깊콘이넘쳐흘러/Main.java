package 그리디알고리즘.깊콘이넘쳐흘러;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
    그리디
    [problem](https://www.acmicpc.net/problem/17420)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer[]> data = new ArrayList<>();

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++) data.add(new Integer[]{Integer.parseInt(st1.nextToken()),Integer.parseInt(st2.nextToken())});
        data.sort((o1, o2) -> o1[1]-o2[1]);

        Map<Integer,List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int prev = -1;
        for(int i=0;i<n;i++){
            if(data.get(i)[1] != prev) list.add(data.get(i)[1]);

            if(data.get(i)[1] == prev) {
                List<Integer> bf = map.get(data.get(i)[1]);
                bf.add(data.get(i)[0]);
                map.put(data.get(i)[1],bf);
            }else{
                List<Integer> bf = new ArrayList<Integer>();
                bf.add(data.get(i)[0]);
                map.put(data.get(i)[1],bf);
            }

            prev = data.get(i)[1];
        }


        long res=0;
        long min=0;
        for(int i=0, size=list.size();i<size;i++){
            int d = list.get(i);
            List<Integer> rl = map.get(d);
            long bmin = 0;
            for(int j=0;j<rl.size();j++){
                Long r = Long.valueOf(rl.get(j));
                if(r<d){
                    long cnt = (d-r)/30 + ((d-r)%30>0?1:0);
                    r += cnt*30;
                    res +=cnt;
                }

                if(r<min){
                    long cnt = (min-r)/30 + ((min-r)%30>0?1:0);
                    r += cnt*30;
                    res +=cnt;
                }

                bmin = Math.max(bmin,r);
            }
            min = bmin;
        }

        System.out.println(res);

        br.close();
    }
}
