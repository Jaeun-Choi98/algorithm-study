package bruteforce.NM과K;

import java.io.*;
import java.util.StringTokenizer;

/*
Bruteforce(백트래킹)
[problem](https://www.acmicpc.net/problem/18290)

- 문제 복기
1. 현재 컬의 위치를 다음 재귀문으로 넘겨서 풀 생각해보기 -> 연산 처리를 줄일 수 있음, 논리적 오류를 범할 수도 있음
2. 방문(visited, check)처리가 중복될 경우 boolean 자료형보단 int자료형으로 표현해보기
 */

public class Main {
    static int n,m,k;
    static int[][] map;
    static int[][] check;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        check = new int[n][m];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        result = Integer.MIN_VALUE;
        search(0,0,0);
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int cur, int sum, int cnt){
        if(cnt == k){
            //System.out.println(sum);
            result = Math.max(result,sum);
            return;
        }

        for(int i=cur;i<n*m;i++){
            int x = i/m;
            int y = (i-x*m)%m;
            if(check[x][y] > 0) continue;
            for(int j=0;j<4;j++){
                int nx = x + dx[j];
                int ny = y + dy[j];
                if(nx<0||ny<0||nx>=n||ny>=m) continue;
                check[nx][ny] += 1;
            }
            search(i+1,sum+map[x][y],cnt+1);
            for(int j=0;j<4;j++){
                int nx = x + dx[j];
                int ny = y + dy[j];
                if(nx<0||ny<0||nx>=n||ny>=m) continue;
                check[nx][ny] -= 1;
            }
        }
    }

}
