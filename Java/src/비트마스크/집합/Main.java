package 비트마스크.집합;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int bitmask =0;
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if(cmd.equals("add")){
                int x = Integer.parseInt(st.nextToken());
                bitmask = bitmask | (1<<(x-1));
            }else if(cmd.equals("remove")){
                int x = Integer.parseInt(st.nextToken());
                if((bitmask & (1<<(x-1)))>0){
                    bitmask = bitmask & (~(1<<(x-1)));
                }
            }else if(cmd.equals("check")){
                int x = Integer.parseInt(st.nextToken());
                if((bitmask & (1<<(x-1)))>0){
                    bw.write(1+"\n");
                }else{
                    bw.write(0+"\n");
                }
            }else if(cmd.equals("toggle")){
                int x = Integer.parseInt(st.nextToken());
                if((bitmask & (1<<(x-1)))>0){
                    bitmask = bitmask & (~(1<<(x-1)));
                }else{
                    bitmask = bitmask | (1<<(x-1));
                }
            }else if(cmd.equals("all")){
                bitmask = bitmask|((1<<20)-1);
            }else if(cmd.equals("empty")){
                bitmask = bitmask & (~bitmask);
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
