package 자료구조.스택.괄호;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int N;
        int count;
        Stack<Character> stack = new Stack<>();
        String buf;
        char chBuf;
        boolean check;
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<N;i++){
            buf = sc.nextLine();
            //System.out.println(buf);
            for(int j=0;j<buf.length();j++){
                stack.push(buf.charAt(j));
            }
            count =0;
            check = true;
            while (!stack.empty()){
                chBuf = stack.pop();
                if(chBuf == ')'){
                    count++;
                }else{
                    count--;
                    while(stack.pop() != ')'){
                        count--;
                    }
                    if(count !=0) {
                        System.out.println("NO");
                        System.out.println(count);
                        check = false;
                        break;
                    }else{
                        count++;
                    }
                }
            }
            if(check) System.out.println("YES");
        }
    }

}
