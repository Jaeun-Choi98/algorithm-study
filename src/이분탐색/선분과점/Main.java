package 이분탐색.선분과점;

import java.io.*;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    /*
    이분탐색
    [problem](https://www.acmicpc.net/problem/11664)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double[] a = new double[3];
        for (int i = 0; i < 3; i++) a[i] = Double.parseDouble(st.nextToken());

        double[] b = new double[3];
        for (int i = 0; i < 3; i++) b[i] = Double.parseDouble(st.nextToken());

        double[] c = new double[3];
        for (int i = 0; i < 3; i++) c[i] = Double.parseDouble(st.nextToken());

        double ac = Math.sqrt(Math.pow(a[0]-c[0],2) + Math.pow(a[1]-c[1],2) + Math.pow(a[2]-c[2],2));
        double bc = Math.sqrt(Math.pow(b[0] - c[0], 2) + Math.pow(b[1] - c[1], 2) + Math.pow(b[2] - c[2], 2));


        double[] mid = new double[3];
        double mc;
        do {
            mid[0] = (a[0]+b[0])/2;
            mid[1] = (a[1]+b[1])/2;
            mid[2] = (a[2]+b[2])/2;
            mc = Math.sqrt(Math.pow(mid[0] - c[0], 2) + Math.pow(mid[1] - c[1], 2) + Math.pow(mid[2] - c[2], 2));
            /*System.out.println(ac);
            System.out.println(bc);*/
            if(ac<=bc) {
                b[0] = mid[0];
                b[1] = mid[1];
                b[2] = mid[2];
                bc = mc;
            } else {
                ac = mc;
                a[0] = mid[0];
                a[1] = mid[1];
                a[2] = mid[2];
            }
        }while(Math.abs(ac-bc) >= 0.00000000001);

        bw.write(String.format("%.10f", bc));
        bw.flush();bw.close();br.close();
    }
}
