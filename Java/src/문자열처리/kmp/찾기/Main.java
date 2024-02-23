package 문자열처리.kmp.찾기;

import java.io.*;
import java.util.ArrayList;

public class Main {
    static String T, P;
    static int[] pi;
    static ArrayList<Integer> res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = br.readLine();
        P = br.readLine();
        pi = new int[P.length()];
        res = new ArrayList<>();
        getPartialMatch();
        kmpSearch();
        bw.write(res.size()+"\n");
        for(int i=0;i<res.size();i++){
            bw.write(res.get(i)+ " ");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static void getPartialMatch(){
        int begin = 1;
        int match = 0;
        int m = P.length();

        while(begin+match<m){
            if(P.charAt(match)==P.charAt(begin+match)){
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

    public static void kmpSearch(){
        int n = T.length();
        int m = P.length();
        int begin = 0;
        int match = 0;

        while (begin+m<=n){
            if(match<m && T.charAt(begin+match) == P.charAt(match)){
                match++;
                if(match==m){
                    res.add(begin+1);
                }
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
