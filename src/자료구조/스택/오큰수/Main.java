package 자료구조.스택.오큰수;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int N;
        int[] arr;
        int buf;
        Stack<Integer> stack = new Stack<>();
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();

        // 1.
        N = scanner.nextInt();

        arr = new int[N];
        for(int i=0;i<N;i++) arr[i] = scanner.nextInt();

        // 2.

        //(1)
        buf = arr[N-1];
        stack.push(-1);

        //(2)
        for(int i= N-2;i>=0;i--){
            //(3)
            if(arr[i] < arr[i+1]) buf = arr[i+1];

            if(buf <= arr[i]) stack.push(-1);
            else{
                stack.push(buf);
            }
        }

        //3.
        while (!stack.empty()){
            stringBuilder.append(stack.pop()).append(" ");
        }

        System.out.println(stringBuilder);
    }
}

