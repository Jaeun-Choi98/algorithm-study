package bruteforce.수학은비대면강의입니다;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a,b,c,d,e,f;
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());

        bw.write( (c*e-b*f)/(a*e-b*d) + " " + (c*d-a*f)/(b*d-a*e));
        bw.flush();
        bw.close();
        br.close();
    }
}
