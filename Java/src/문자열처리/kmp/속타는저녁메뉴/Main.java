package 문자열처리.kmp.속타는저녁메뉴;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    kmp 알고리즘
    [problem](https://www.acmicpc.net/problem/11585)
     */
    static int n;
    static String T,P;
    static int[] pi;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        pi = new int[n];
        for(int i=0;i<n;i++){
            sb.append(st.nextToken());
        }
        T = sb.toString();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            sb.append(st.nextToken());
        }
        P = sb.toString();
        getPartialMatch();
        cnt = 0;
        kmp();
        int val = gcd(n,cnt);
        bw.write(cnt/val + "/" + n/val + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
    public static int gcd(int a, int b){
        while(b!=0){
            int tmp = b;
            b = a%b;
            a = tmp;
        }
        return a;
    }

    public static void kmp(){
        int begin = 0;
        int match = 0;
        while (begin<n){
            if(match<n && P.charAt(match)==T.charAt((begin+match)%n)){
                match++;
                if(match==n) {
                    cnt++;
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

    public static void getPartialMatch(){
        int begin = 1;
        int match = 0;

        while (begin+match<n){
            if(P.charAt(match)==P.charAt(begin+match)){
                match++;
                pi[begin+match-1]=match;
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
