package DFS와BFS.알파벳;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*
    Bruteforce(DFS)
    [problem](https://www.acmicpc.net/problem/1987)

    BFS방법으로 풀 경우, 이전에 방문했던 알파벳을 기록할 때, 자신의 부모 노드들의 알파벳만 저장해야 합니다.
    따라서, 다음 노드로 방문할 때, 자신의 부모 노드들의 정보를 담는 자료 구조를 넘겨줘야 하기 때문에 불필요한 메모리 사용이 있을 수 있습니다.
     */
    static int n, m;
    static char[][] map;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static boolean[] check;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][];

        for(int i=0;i<n;i++){
            map[i] = br.readLine().toCharArray();
        }

        check = new boolean[26];

        check[map[0][0]-'A'] = true;
        res = 1;
        search(0,0, 1);


        bw.write(res + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int x, int y, int sum){
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0||ny<0||nx>=n||ny>=m) continue;
            if(check[map[nx][ny]-'A']) continue;
            check[map[nx][ny]-'A'] = true;
            res = Math.max(res,sum+1);
            search(nx,ny,sum+1);
            check[map[nx][ny]-'A'] = false;
        }
    }
}
