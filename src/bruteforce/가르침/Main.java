package bruteforce.가르침;

import java.io.*;
import java.util.*;

public class Main {
    /*
    bruteforce, 조합
    [problem](https://www.acmicpc.net/problem/1062)
     */
    static boolean[] check;
    static int n,k,res;
    static List<Integer> list;
    static List<Integer>[] dataList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if(k<5){
            res = 0;
            bw.write(res + "\n");
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        check = new boolean[26];
        list = new ArrayList<>();
        dataList = new ArrayList[n];
        check['a'-'a'] = true;
        check['n'-'a'] = true;
        check['t'-'a'] = true;
        check['i'-'a'] = true;
        check['c'-'a'] = true;

        for(int i=0;i<n;i++){
            String buf = br.readLine();
            dataList[i] = new ArrayList<>();
            for(int j=0;j<buf.length();j++){
                if(check[buf.charAt(j)-'a']) continue;
                dataList[i].add(buf.charAt(j) - 'a');
                if(list.contains(buf.charAt(j)-'a')) continue;
                list.add(buf.charAt(j) - 'a');
            }
        }
        /*System.out.println(list.size());
        list.stream().forEach(s-> System.out.println((char)(s+'a')));*/
        if(list.size()<=k-5) res = n;
        else{
            res = Integer.MIN_VALUE;
            search(0,5);
        }

        bw.write(res+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int cur, int cnt){
        if(cnt==k){
            int sum = 0;
            for(int i=0;i<n;i++){
                boolean flag = true;
                for(int j=0;j<dataList[i].size();j++){
                    if(!check[dataList[i].get(j)]) {
                        flag = false;
                        break;
                    }
                }
                if(flag) sum++;
            }
            res = Math.max(sum,res);
            return;
        }

        for(int i=cur;i<list.size();i++){
            check[list.get(i)] = true;
            search(i+1,cnt+1);
            check[list.get(i)] = false;
        }
    }
}
