package DFS와BFS.그림;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int x,y,count,size,bufSize;
        int[][] board;
        boolean[][] check;
        XY xy;
        int[] dx = {0,-1,0,1};
        int[] dy = {1,0,-1,0};
        Queue<XY> queue = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        x = scanner.nextInt(); y = scanner.nextInt();
        board = new int[x][y];
        check = new boolean[x][y];

        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                board[i][j] = scanner.nextInt();
            }
        }

        count =0;
        size = 0;
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if(check[i][j] || board[i][j] ==0) continue;
                check[i][j] = true;
                count++;
                bufSize =0;
                xy = new XY(i,j);
                queue.add(xy);
                while (!queue.isEmpty()){
                    bufSize++;
                    xy = queue.poll();
                    for(int k=0;k<4;k++){
                        int nx = xy.getX()+dx[k];
                        int ny = xy.getY()+dy[k];
                        if(nx<0||nx>=x||ny<0||ny>=y) continue;
                        if(check[nx][ny] || board[nx][ny] == 0) continue;
                        check[nx][ny] = true;
                        queue.add(new XY(nx,ny));
                    }
                }
                size = Math.max(bufSize,size);
            }
        }
        System.out.println(count);
        System.out.println(size);
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
