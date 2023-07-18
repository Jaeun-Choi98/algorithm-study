package 이분탐색.랜선자르기;

import java.io.*;
import java.util.StringTokenizer;

/*
이분탐색, 매개 변수 탐색
[problem](https://www.acmicpc.net/problem/1654)

문제 복기
이분 탐색을 쓸 경우 항상 마지막 경우의 수를 생각해야한다. (찾는 값이 start이거나 end일 경우)
만약 while 문에서 start<end로 할 경우 답이 start나 end일 경우 마지막 정답 루프를 패싱한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] datas = new int[n];
        long end = Integer.MIN_VALUE;
        long start = 1;
        for(int i=0;i<n;i++){
            datas[i] = Integer.parseInt(br.readLine());
            end = Math.max(end,datas[i]);
        }

        long result = 0;
        while(start<=end){
            long mid = (start+end)/2;
            long cnt = 0;
            for(int i=0;i<n;i++){
                cnt += datas[i]/mid;
            }
            //System.out.println(mid);
            if(cnt>=k){
                result = Math.max(result,mid);
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
