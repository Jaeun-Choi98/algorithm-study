package 문자열처리.kmp.카멜레온부분문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /*
    kmp 알고리즘
    [problem](https://www.acmicpc.net/problem/13506)

    30: when(len>0)을 하는 이유 -> 반례: ababcabab
     */
    static String s;
    static int[] pi;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        pi = new int[s.length()];
        getPartialMatch();
        String res = search();
        if(res.isEmpty()) System.out.println(-1);
        else System.out.println(res);
        br.close();
    }

    static String search(){
        String res = "";
        int len = pi[s.length()-1];
        while(len>0){
            for(int i=1;i<s.length()-1;i++){
                if(pi[i]==len){
                    res = s.substring(i-len+1,i+1);
                    return res;
                }
            }
            len = pi[len-1];
        }
        return "";
    }

    static void getPartialMatch(){
        int begin = 1;
        int match = 0;
        while (begin+match<s.length()){
            if(s.charAt(match) == s.charAt(begin+match)){
                match++;
                pi[begin+match-1] = match;
            }else{
                if(match==0) begin++;
                else{
                    begin += match - pi[match-1];
                    match = pi[match-1];
                }
            }
        }
    }
}
