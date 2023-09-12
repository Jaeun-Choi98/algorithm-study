package DFS와BFS.소수경로;

import java.io.*;
import java.util.*;

public class Main {
    /*
    BFS, 에라토스테네스의 체
    [problem](https://www.acmicpc.net/problem/1963)
     */
    static boolean[] check,visit;
    static int a,b;
    static int[] d;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        check = new boolean[10000];
        check[1] = true;
        for(int i=2;i<Math.sqrt(10000);i++){
            if(check[i]) continue;
            for(int j=i+i;j<10000;j+=i){
                check[j] = true;
            }
        }



        int tc = Integer.parseInt(br.readLine());
        while(tc!=0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            search();
            bw.write(d[b] + "\n");
            tc--;
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(){
        d = new int[10000];
        visit = new boolean[10000];
        Queue<Integer> que = new LinkedList<>();
        que.add(a);
        visit[a] = true;
        while (!que.isEmpty()){
            int buf = que.poll();
            for(int i=0;i<4;i++){
                for(int j=0;j<10;j++){
                    if(i==0 && j==0) continue;
                    int ch = change(buf,i,j);
                    if(check[ch] || visit[ch]) continue;
                    visit[ch] = true;
                    que.add(ch);
                    d[ch] = d[buf] + 1;
                }
            }
        }
    }

    public static int change(int a, int i, int j){
        String str = String.valueOf(a);
        char[] chars = new char[4];
        for(int l=0;l<4;l++){
            chars[l] = str.charAt(l);
        }
        chars[i] = (char) (j + '0');
        str="";
        for(int l=0;l<4;l++){
            str += chars[l];
        }
        return Integer.valueOf(str);
    }
}
