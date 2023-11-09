package 문자열처리.문자열폭발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static char[] data;
    static char[] tar;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        data = br.readLine().toCharArray();
        tar = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<data.length;i++){
            stack.push(data[i]);
            if(stack.size()>=tar.length && stack.peek() == tar[tar.length-1]){
                boolean check = true;
                for(int j=1;j<tar.length;j++){
                    if(stack.get(stack.size()-1-j)!=tar[tar.length-1-j]) {
                        check = false;
                        break;
                    }
                }
                if(check){
                    for(int j=0;j<tar.length;j++){
                        stack.pop();
                    }
                }
            }
        }
        if(stack.isEmpty()){
            System.out.println("FRULA");
            return;
        }
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        sb.reverse();
        System.out.println(sb);
        br.close();
    }
}
