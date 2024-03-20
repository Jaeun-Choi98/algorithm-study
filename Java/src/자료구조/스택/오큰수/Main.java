package 자료구조.스택.오큰수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    /*
    stack
    [problem](https://www.acmicpc.net/problem/17298)
     */
    public static void main(String[] args) throws IOException {
        int N;
        Stack<Integer[]> stack = new Stack<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] res = new int[N];

        for(int i=0;i<N;i++) {
            int buf = Integer.parseInt(st.nextToken());
            if(stack.isEmpty()) {
                stack.push(new Integer[]{i,buf});
                continue;
            }
            while(!stack.isEmpty() && stack.peek()[1] < buf){
                Integer[] idxAndVal = stack.pop();
                res[idxAndVal[0]] = buf;
            }
            stack.push(new Integer[]{i,buf});
        }

        while (!stack.isEmpty()){
            Integer[] idxAndVal = stack.pop();
            res[idxAndVal[0]] = -1;
        }
        StringBuilder sb = new StringBuilder();
        Arrays.stream(res).forEach(i-> sb.append(i+ " "));
        System.out.println(sb);
        br.close();
    }
}

