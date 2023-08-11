package bruteforce.암호만들기;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
    Bruteforce
    [porblem](https://www.acmicpc.net/problem/1759)
     */
    static int l,c;
    static String[] datas;
    static StringBuilder sb;
    static char[] moum = {'a', 'e', 'i', 'o', 'u'};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        datas = new String[c];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<c;i++){
            datas[i] = st.nextToken();
        }
        Arrays.sort(datas);
        //Arrays.stream(datas).forEach(s-> System.out.print(s));

        sb = new StringBuilder();
        search(0,0);
    }

    public static void search(int idx, int cnt){
        if(cnt==l){
            check();
            return;
        }

        for(int i=idx;i<c;i++){
            sb.append(datas[i]);
            search(i+1,cnt+1);
            sb.deleteCharAt(cnt);
        }
    }

    public static void check(){
        int mo = 0;
        int ja = 0;
        for(int i=0;i<sb.length();i++){
            char buf = sb.charAt(i);
            boolean flag = false;
            for(int j=0;j<moum.length;j++){
                if(moum[j] == buf) flag = true;
            }

            if(flag) mo++;
            else ja++;
        }
        if(mo>=1 && ja >=2) System.out.println(sb);

    }
}
