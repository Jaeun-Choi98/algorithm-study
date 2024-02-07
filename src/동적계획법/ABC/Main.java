package 동적계획법.ABC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    dp
    [problem](https://www.acmicpc.net/problem/12969)
    Top down 방식의 경우, 점화식이 아닌 완전탐색 문제에서도 활용된다.
    중복되는 부분을 찾아 메모리에 저장한다.  ex)외판원 순회 문제, 백준-내리막길, 파이프연결하기2, 출근기록
     */
    static int n,k;
    static boolean[][][][] check;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        check = new boolean[n+1][n+1][n+1][k+1];
        search(0,0,0,0,"");
        if(!flag) System.out.println(-1);
        br.close();
    }

    static void search(int a, int b, int c, int kVal, String res){
        if(flag) return;
        if(a+b+c == n) {
            if(kVal==k) {
                System.out.println(res);
                flag = true;
            }
            return;
        }
        //System.out.printf("%d %d %d %s %d\n",a,b,c,res,kVal);

        if(check[a][b][c][kVal]) return;
        check[a][b][c][kVal] = true;

        // c
        if(kVal+a+b<=k) search(a,b,c+1,kVal+a+b,res+"C");
        if(kVal+a<=k) search(a,b+1,c,kVal+a,res+"B");
        search(a+1,b,c,kVal,res+"A");
    }
}
