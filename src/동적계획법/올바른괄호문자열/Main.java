package 동적계획법.올바른괄호문자열;

import java.awt.image.LookupOp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/3012)
     */
    static int n;
    static String data;


    static char[] dc2 = {')', '}', ']'};
    static char[] dc1 = {'(', '{', '['};

    static Long[][] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data = br.readLine();
        d = new Long[n][n];
        Long res = search(0,n-1);
        if(res >= 100000) System.out.printf("%05d\n", res%100000);
        else System.out.println(res);
        br.close();
    }

    static Long search(int s, int e){
        if(s>e) {
            return 1L;
        }

        if(d[s][e] != null) return d[s][e];
        d[s][e] = 0L;

        for(int i=s+1;i<=e;i+=2){
            for(int j=0;j<3;j++){
                if(data.charAt(s)==dc1[j] || data.charAt(s) == '?'){
                    if(data.charAt(i)==dc2[j] || data.charAt(i) == '?'){
                        d[s][e] += (search(s+1,i-1) *search(i+1,e));
                        if(d[s][e] >= 100000) d[s][e] = 100000 + d[s][e]%100000;
                    }
                }
            }
        }
        return d[s][e];
    }
}
