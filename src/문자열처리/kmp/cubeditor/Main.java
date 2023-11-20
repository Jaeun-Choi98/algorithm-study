package 문자열처리.kmp.cubeditor;

import java.io.*;

public class Main {
    /*
    문자열 처리, kmp
    [problem](https://www.acmicpc.net/problem/1701)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String src = br.readLine();

        int res = 0;
        for(int i=0;i<src.length();i++){
            String subSrc = src.substring(i);
            int begin = 1;
            int match = 0;
            int n = subSrc.length();
            int[] pi = new int[n];
            while(begin + match <n){
                if(subSrc.charAt(match) == subSrc.charAt(match+begin)){
                    match++;
                    res = Math.max(match,res);
                    pi[match+begin-1] = match;
                }else{
                    if(match==0) begin++;
                    else{
                        begin += match - pi[match-1];
                        match = pi[match-1];
                    }
                }
            }
            /*
            for(int j=0;j<n;j++){
                System.out.printf(pi[j]+" ");
            }
            System.out.println("");
            */
        }

        bw.write(res+"\n");
        br.close();
        bw.flush();
        bw.close();
    }
}
