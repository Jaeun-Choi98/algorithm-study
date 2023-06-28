package bruteforce.설탕배달;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int fiveCnt = 0;
        int threeCnt = 0;
        boolean flag = false;
        int res = 0;

        fiveCnt = n/5;
        for(;fiveCnt>=0;fiveCnt--){
            int buf = n;
            buf -= fiveCnt*5;
            if(buf%3 == 0){
                threeCnt = buf/3;
                flag = true;
                break;
            }
        }

        if(flag){
            bw.write(threeCnt+fiveCnt + "\n");
        }
        else{
            bw.write(-1 + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
