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
            stack.clear();
            check = true;
            for(int j=0;j<buf.length();j++){
                chBuf = buf.charAt(j);
                if(chBuf == ')') {
                    if(stack.empty()) {
                        check = false;
                        break;
                    }
                    stack.pop();
                }
                else stack.push(buf.charAt(j));
            }
            if(stack.empty() && check == true) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
