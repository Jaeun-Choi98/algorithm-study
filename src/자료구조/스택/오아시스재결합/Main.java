package 자료구조.스택.오아시스재결합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class Main {
    /*
    자료구조, 스택
    [problem](https://www.acmicpc.net/problem/3015)
     */
    static int n;
    static int[] datas;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        datas = new int[n];
        //StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            datas[i] = Integer.parseInt(br.readLine());
        }
        Stack<Integer[]> stack = new Stack<>();
        stack.push(new Integer[]{datas[0],0});
        long res = 0;
        for(int i=1;i<n;i++){
            Integer[] cur = {datas[i],0};
            long cnt = 1;
            while(!stack.isEmpty() && stack.peek()[0] < cur[0] ){
                stack.pop();
                cnt++;
            }
            if(stack.isEmpty()) cnt--;
            res += cnt;

            // 중복처리
            if(!stack.isEmpty() && stack.peek()[0].equals(cur[0])){
                cur[1]++;
                cur[1] += stack.peek()[1];
                if(stack.size()==1) cur[1]--;
                res+= cur[1];
            }
            stack.push(cur);
            /*for(int j=0;j<stack.size();j++){
                System.out.printf(stack.get(j)[0] + " ");
            }
            System.out.println("");
            System.out.println(cnt);*/

        }
        System.out.println(res);
        br.close();
    }
}
