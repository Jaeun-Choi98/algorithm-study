package 동적계획법.출근기록;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/14238)
    Top down 방식의 경우, 점화식이 아닌 완전탐색 문제에서도 활용된다.
    중복되는 부분을 찾아 메모리에 저장한다.  ex)외판원 순회 문제, 백준-내리막길, 파이프연결하기2
     */
    static int a,b,c;
    static boolean flag;
    static Map<String,Boolean> check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String data = br.readLine();
        for(int i=0;i<data.length();i++){
            char buf = data.charAt(i);
            if(buf=='A') a++;
            else if(buf=='B') b++;
            else c++;
        }
        check = new HashMap<>();
        search(a,b,c,' ',' ',"");
        if(!flag) System.out.println(-1);
        br.close();
    }

    static void search(int aCnt, int bCnt, int cCnt, char pre2, char pre1, String cur){
        if(aCnt==0 && bCnt==0 && cCnt==0){
            flag = true;
            System.out.println(cur);
            return;
        }
        String checkVal = String.valueOf(aCnt)+String.valueOf(bCnt)+String.valueOf(cCnt)+pre2+pre1;
        if(check.containsKey(checkVal)) return;
        check.put(checkVal,true);
        if(aCnt>0) search(aCnt-1,bCnt,cCnt,pre1,'A',cur+"A");
        if(bCnt>0 && pre1 !='B') search(aCnt,bCnt-1,cCnt,pre1,'B',cur+"B");
        if(cCnt>0 && pre2 !='C' && pre1 != 'C') search(aCnt,bCnt,cCnt-1,pre1,'C',cur+"C");
    }
}