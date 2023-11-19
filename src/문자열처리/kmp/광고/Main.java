package 문자열처리.kmp.광고;

import java.io.*;

public class Main {
    /*
    문자열 처리, kmp 알고리즘
    [problem](https://www.acmicpc.net/problem/1305)
     */
    static int L;
    static String src;
    static int[] pi;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        L = Integer.parseInt(br.readLine());
        src = br.readLine();
        int n = src.length();
        pi = new int[n];

        int begin = 1;
        int match = 0;

        while(begin + match < n){
            if(src.charAt(match) == src.charAt(begin+match)){
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
        bw.write(n-pi[n-1] + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}
