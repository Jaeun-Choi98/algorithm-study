package DFS와BFS.토마토;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N,M;
        int[][] board,dis;
        boolean[][] check;
        Queue<XY> queue= new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};

        M=scanner.nextInt(); N = scanner.nextInt();
        board = new int[N][M]; dis = new int[N][M]; check = new boolean[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                board[i][j] = scanner.nextInt();
                if(board[i][j] == 1){
                    queue.add(new XY(i,j));
                    check[i][j] = true;
                }
                else if(board[i][j] == 0) dis[i][j] = -1;
            }
        }

        while (!queue.isEmpty()){
            XY xy = queue.poll();
            for(int dir =0;dir<4;dir++){
                int nx = xy.getX() + dx[dir];
                int ny = xy.getY() + dy[dir];
                if(nx<0||nx>=N||ny<0||ny>=M) continue;
                if(board[nx][ny]==-1||check[nx][ny]) continue;
                check[nx][ny] = true;
                queue.add(new XY(nx,ny));
                dis[nx][ny] = dis[xy.getX()][xy.getY()] + 1;
            }
        }

        /*StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                stringBuilder.append(dis[i][j]).append(" ");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);*/

        int maxDay=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(dis[i][j] == -1) {
                    System.out.println(-1);
                    return;
                }else{
                    maxDay = Math.max(maxDay,dis[i][j]);
                }
            }
        }

        System.out.println(maxDay);
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
