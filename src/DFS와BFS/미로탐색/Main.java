package DFS와BFS.미로탐색;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int[][] dis;
        int N,M;
        int[][] board;
        boolean[][] check;
        Queue<XY> queue = new LinkedList<>();
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        XY xy;
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt(); M = scanner.nextInt(); scanner.nextLine();
        board = new int[N][M];
        check = new boolean[N][M];
        dis = new int[N][M];

        for (int i=0;i<N;i++){
            String str = scanner.nextLine();
            for(int j=0;j<str.length();j++){
                board[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }

        //항상 도착위치로 이동할 수 있는 경우만 입력으로 주어짐
        queue.add(new XY(0,0));
        check[0][0] = true;
        dis[0][0] = 1;
        while (!queue.isEmpty()){
            xy = queue.poll();
            for(int dir=0;dir<4;dir++){
                int nx = xy.getX() + dx[dir];
                int ny = xy.getY() + dy[dir];
                if(nx<0||nx>=N||ny<0||ny>=M) continue;
                if(board[nx][ny]==0) continue;
                if(check[nx][ny]){
                    continue;
                }
                check[nx][ny] = true;
                queue.add(new XY(nx,ny));
                dis[nx][ny] = dis[xy.getX()][xy.getY()] + 1;
            }
        }
        System.out.println(dis[N-1][M-1]);
    }
}

class XY{
    private int x;
    private int y;

    public XY(int x,int y){
        this.x = x; this.y =y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}


