package 분할정복.곱셈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    /*
    분할정복
    [problem](https://www.acmicpc.net/problem/1629)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a,b,c;
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        System.out.printf("%d ", search(a,b,c));
        br.close();
    }

    static long search(int a, int b, int c){
        if(b==1) return a%c;

        long val = search(a,b/2,c);

        if(b%2==0) return (val * val) % c;
        else return ((val * val) % c) * a % c;
    }
}
