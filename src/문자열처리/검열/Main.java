package 문자열처리.검열;

import java.io.*;
import java.util.Stack;

public class Main {
    /*
    문자열 처리
    [problem](https://www.acmicpc.net/problem/3111)
     */
    static StringBuilder sb;
    static String a,text;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        a = br.readLine();
        text = br.readLine();

        sb = new StringBuilder();
        search();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(){
        Stack<Character> lst = new Stack<>();
        Stack<Character> rst = new Stack<>();

        int l = 0;
        int r = text.length()-1;

        int aleng = a.length();
        while(l<=r){
            while(l<=r){
                lst.push(text.charAt(l++));
                if(lst.size()>=aleng && lst.peek()==a.charAt(aleng-1)){
                    boolean check = true;
                    for(int i=0;i<aleng;i++){
                        if(lst.get(lst.size()-1-i)!=a.charAt(aleng-1-i)) {
                            check = false;
                            break;
                        }
                    }
                    if(check){
                        for(int i=0;i<aleng;i++){
                            lst.pop();
                        }
                        break;
                    }
                }
            }
            while(l<=r){
                rst.push(text.charAt(r--));
                if(rst.size()>=aleng && rst.peek()==a.charAt(0)){
                    boolean check = true;
                    for(int i=0;i<aleng;i++){
                        if(rst.get(rst.size()-1-i) != a.charAt(i)){
                            check = false;
                            break;
                        }
                    }
                    if(check){
                        for(int i=0;i<aleng;i++){
                            rst.pop();
                        }
                        break;
                    }
                }
            }
        }


        while(!lst.isEmpty()){
            rst.push(lst.pop());
            if(rst.size()>=aleng && rst.peek()==a.charAt(0)){
                boolean check = true;
                for(int i=0;i<aleng;i++){
                    if(rst.get(rst.size()-1-i) != a.charAt(i)){
                        check = false;
                        break;
                    }
                }
                if(check){
                    for(int i=0;i<aleng;i++){
                        rst.pop();
                    }
                }
            }
        }
        while (!rst.isEmpty()){
            sb.append(rst.pop());
        }

    }
}
