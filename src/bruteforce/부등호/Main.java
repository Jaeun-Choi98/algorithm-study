package bruteforce.부등호;

import java.io.*;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    /*
    bruteforce
    [problem](https://www.acmicpc.net/problem/2529)
     */
    static int n;
    static long resMax,resMin;
    static StringBuilder resMaxStr, resMinStr;
    static String[] datas;
    static boolean[] check = new boolean[10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        datas = new String[n];
        for(int i=0;i<n;i++){
            datas[i] = st.nextToken();
        }
        resMax = Long.MIN_VALUE;
        resMin = Long.MAX_VALUE;
        search(0, new StringBuilder());

        bw.write(resMaxStr + "\n" + resMinStr);
        bw.flush();
        bw.close();
        br.close();
    }

    static public void search(int cnt, StringBuilder buf){
        //System.out.println(buf);
        if(cnt == n+1){
            boolean flag = true;
            for(int i=0;i<n;i++){
                if(!flag) break;
                if(datas[i].equals("<")){
                    if(buf.charAt(i) > buf.charAt(i+1)) flag = false;
                }else{
                    if(buf.charAt(i) < buf.charAt(i+1)) flag = false;
                }
            }
            if(flag){
                if(resMax<Long.valueOf(buf.toString())){
                    resMaxStr = new StringBuilder(buf.toString());
                    resMax = Long.valueOf(buf.toString());
                }
                if(resMin>Long.valueOf(buf.toString())){
                    resMinStr = new StringBuilder(buf.toString());
                    resMin = Long.valueOf(buf.toString());
                }
            }
            return;
        }

        for(int i=0;i<10;i++){
            if(check[i]) continue;
            check[i] = true;
            search(cnt+1,buf.append(i));
            check[i] = false;
            buf.delete(buf.length() - 1, buf.length());
        }
    }
}
