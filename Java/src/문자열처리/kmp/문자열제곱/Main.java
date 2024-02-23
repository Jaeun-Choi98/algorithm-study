package 문자열처리.kmp.문자열제곱;

import java.io.*;

public class Main {
    /*
    kmp 알고리즘
    [problem](https://www.acmicpc.net/problem/4354)

    PartialMatch 배열은 반대로 생각하면 실패 배열이다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true){
            String a = br.readLine();
            if(a.equals(".")) break;

            int[] pi = getPartialMatch(a);
            int size = a.length();
            int checkLeng = size - pi[size-1];

            if(size%checkLeng!=0) bw.write("1\n");
            else bw.write(size/checkLeng + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int[] getPartialMatch(String a){
        int size = a.length();
        int[] pi = new int[size];

        int begin = 1;
        int match = 0;

        while (begin+match<size){
            if(a.charAt(begin+match) == a.charAt(match)){
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

        return pi;
    }
}
