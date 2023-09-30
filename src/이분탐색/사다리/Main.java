package 이분탐색.사다리;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    이분 탐색
    [problem](https://www.acmicpc.net/problem/2022)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double x = Double.parseDouble(st.nextToken());
        double y = Double.parseDouble(st.nextToken());
        double c = Double.parseDouble(st.nextToken());

        double start = 0;
        double end = x;
        while (end - start >= 0.001){
            double mid = (start+end)/2;
            double a1 = Math.sqrt(Math.pow(x,2) - Math.pow(mid,2));
            double a2 = Math.sqrt(Math.pow(y,2) - Math.pow(mid,2));
            double cGuess = a1*a2/(a1+a2);

            if(cGuess >= c) start = mid;
            else end = mid;
        }

        bw.write(String.format("%.3f", end));
        bw.flush();
        bw.close();
        br.close();
    }
}
