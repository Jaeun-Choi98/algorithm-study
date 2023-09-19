package DFS와BFS.사연산;

import java.io.*;
import java.util.*;

public class Main {
    /*
    BFS
    [problem](https://www.acmicpc.net/problem/14395)
     */
    static long a,b;
    static List<Long> check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Long.valueOf(st.nextToken());
        b = Long.valueOf(st.nextToken());
        if(a==b){
            System.out.println(0);
            return;
        }
        check = new ArrayList<>();
        search();
    }

    public static void search(){
        Queue<String[]> que = new LinkedList<>();
        que.add(new String[]{String.valueOf(a),""});
        check.add(a);
        boolean flag = false;
        while (!que.isEmpty()) {
            String[] buf = que.poll();
            Long x = Long.valueOf(buf[0]);
            String record = buf[1];

            //System.out.println(x);
            if(x == b){
                System.out.println(record);
                flag = true;
                break;
            }

            if(!check.contains(x*x) && x*x <= b){
                que.add(new String[]{String.valueOf(x * x), record + "*"});
                check.add(x*x);
            }
            if(!check.contains(x+x) && x+x <= b){
                que.add(new String[]{String.valueOf(x + x), record + "+"});
                check.add(x+x);
            }
            if(!check.contains(x-x)){
                que.add(new String[]{String.valueOf(x - x), record + "-"});
                check.add(x-x);
            }
            if(x!=0 && !check.contains(x/x)){
                que.add(new String[]{String.valueOf(x / x), record + "/"});
                check.add(x/x);
            }

        }

        if(!flag) System.out.println(-1);
    }
}
