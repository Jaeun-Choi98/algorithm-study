package 이분탐색.배열에서이동;

import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


/*
이분탐색 + BFS
[problem](https://www.acmicpc.net/problem/1981)

-문제복기
처음에 우선순위큐를 이용해서 풀려고 했지만 중복으로 방문한 배열을 처리하지 못하는 문제가 발생하였다.
그래서 visted[][] 배열과 bitmask를 이용하려 했지만 메모리 초과가 발생하였다.

결과적으로 https://skdltm117.tistory.com/47 블로그를 참고하여 이분탐색을 이용하여 문제를 해결하였다.
 */
public class Main {
    static int n;
    static int[][] map;
    static boolean[][] check;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        check = new boolean[n][n];
        StringTokenizer st;
        int max = -1;
        int min = 201;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(map[i][j],max);
                min = Math.min(map[i][j],min);
            }
        }
        result = 201;

        int start = 0;
        int end = max-min;
        while(start<=end){
            int mid = (start+end)/2;
            boolean flag = false;
            for(int i=min;i<max;i++){
                if(i>map[0][0]||i+mid<map[0][0]) continue;
                if(flag = bfs(i,i+mid)) break;
            }

            if(flag){
                end = mid - 1;
                result = Math.min(result,mid);
            }else{
                start = mid + 1;
            }
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }


    public static boolean bfs(int min, int max){
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{0,0});

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                check[i][j] = false;
            }
        }

        check[0][0] = true;

        while (!q.isEmpty()){
            Integer[] cur = q.poll();
            if(cur[0] == n-1 && cur[1] == n-1){
                return true;
            }
            for(int i=0;i<4;i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0||ny<0||nx>=n||ny>=n||check[nx][ny]) continue;
                if(map[nx][ny] <min || map[nx][ny] >max) continue;
                check[nx][ny] = true;
                q.add(new Integer[]{nx,ny});
            }
        }

        return false;
    }
}

