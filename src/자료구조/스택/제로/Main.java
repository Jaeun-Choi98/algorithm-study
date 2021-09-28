package 자료구조.스택.제로;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int K, buf;
        int sum=0;
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();

        K = sc.nextInt();
        for(int i=0;i<K;i++){
            buf = sc.nextInt();
            if(buf == 0) {
                stack.pop();
                continue;
            }
            stack.push(buf);
        }
        for(int i:stack) sum += i;
        System.out.println(sum);
    }
}
