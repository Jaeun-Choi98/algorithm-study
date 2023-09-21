package 분할정복.사분면;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    /*
    분할정복
    [problem](https://www.acmicpc.net/problem/1891)
     */
    static int lengD;
    static String d;
    static long moveX,moveY;
    static long x,y;
    static String res;
    public static void main(String[] args) throws IOException {
        //Scanner scanner = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        lengD = Integer.parseInt(st.nextToken());
        d = st.nextToken();
        st = new StringTokenizer(br.readLine());
        moveY = Long.valueOf(st.nextToken());
        moveX = Long.valueOf(st.nextToken());

        x = 0;
        y = 0;
        long size = (long)Math.pow(2,lengD);
        search(0,size/2);

        x -= moveX;
        y += moveY;
        res = "";
        if(x>=0&&x<size && y>=0&&y<size){
            search1(size/2);
        }else System.out.println(-1);

        System.out.println(res);
        br.close();
    }
    public static void search1(long size){
        if(size==0){
            return;
        }

        if(x>=0 && x<size && y>=size && y<2*size) {
            y-=size;
            res += "1";
            search1(size/2);
        }else if(x>=0 && x<size && y>=0 && y<size){
            res += "2";
            search1(size/2);
        }else if(x>=size && x<2*size && y>=0 && y<size){
            x-=size;
            res += "3";
            search1(size/2);
        }else if(x>=size && x<2*size && y>=size && y<2*size){
            x-=size;
            y-=size;
            res += "4";
            search1(size/2);
        }
    }

    public static void search(int curIdx, long size){
        if(size==0){
            return;
        }
        if(d.charAt(curIdx)=='1'){
            y += size;
            search(curIdx+1,size/2);
        }
        else if(d.charAt(curIdx)=='2'){
            search(curIdx+1,size/2);
        }
        else if(d.charAt(curIdx)=='3'){
            x += size;
            search(curIdx+1,size/2);
        }
        else if(d.charAt(curIdx)=='4'){
            x += size;
            y += size;
            search(curIdx+1,size/2);
        }
    }
}
