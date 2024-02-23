package DFS와BFS.DSLR;

import java.io.*;
import java.util.*;

public class Main {
    /*
    BFS
    [problem](https://www.acmicpc.net/status?user_id=chlwodns98&problem_id=9019&from_mine=1)
     */
    static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());

        while (tc--!=0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            check = new boolean[10000];
            search(a,b);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(String a, String b){
        Queue<String[]> que = new LinkedList<>();
        que.add(new String[]{a, b, ""});
        while (!que.isEmpty()) {
            String[] buf = que.poll();
            int na = Integer.parseInt(buf[0]);
            int nb = Integer.parseInt(buf[1]);
            /*System.out.printf("%s %s %s",buf[0],buf[1],buf[2]);
            System.out.println();*/
            if(na == nb){
                System.out.println(buf[2]);
                break;
            }
            if(check[na]) continue;
            check[na] = true;
            que.add(new String[]{D(na), buf[1], buf[2] + "D"});
            que.add(new String[]{S(na), buf[1], buf[2] + "S"});
            que.add(new String[]{L(na), buf[1], buf[2] + "L"});
            que.add(new String[]{R(na), buf[1], buf[2] + "R"});
        }
    }

    public static String D(int n){
        return String.valueOf((2*n)%10000);
    }

    public static String S(int n){
        if(n==0) return String.valueOf(9999);
        else return String.valueOf(n-1);
    }

    public static String L(int n){
        n = (n%1000)*10 + n/1000;
        return String.valueOf(n);
    }

    public static String R(int n){
        n = (n%10)*1000 + n/10;
        return String.valueOf(n);
    }
}
