package DFS와BFS.불;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int R,C;
        char[][] board;
        int[][] disF,disJ;
        boolean[][] checkF,checkJ;
        Queue<XY> queue= new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};

        R=scanner.nextInt(); C = scanner.nextInt();
        board = new char[R][C]; disF = new int[R][C]; disJ = new int[R][C];
        checkF = new boolean[R][C]; checkJ = new boolean[R][C];

        //값 초기화(불의 거리를 먼저 구하기 위한)
        scanner.nextLine();
        for(int i =0;i<R;i++){
            String string = scanner.nextLine();
            for(int j=0;j<string.length();j++){
                board[i][j] = string.charAt(j);
                if(board[i][j] == 'F') {
                    checkF[i][j] = true;
                    queue.add(new XY(i,j));
                }
            }
        }

        //불의 거리 구하기
        while (!queue.isEmpty()){
            XY xy = queue.poll();
            for(int dir=0;dir<4;dir++){
                int nx = xy.getX() + dx[dir];
                int ny = xy.getY() + dy[dir];
                if(nx<0||nx>=R||ny<0||ny>=C) continue;
                if(checkF[nx][ny]||board[nx][ny]=='#') continue;
                disF[nx][ny] = disF[xy.getX()][xy.getY()] + 1;
                checkF[nx][ny] = true;
                queue.add(new XY(nx,ny));
            }
        }

        //지훈이의 거릭 구하기
        //check판 초기화 + 지훈이에 대한 BFS 하기전 초기설정
        queue.clear();
        for(int i =0;i<R;i++){
            for(int j=0;j<C;j++){
                if(board[i][j] == 'J') {
                    disJ[i][j] = 0;
                    checkJ[i][j] = true;
                    queue.add(new XY(i,j));
                }
            }
        }

        while (!queue.isEmpty()){
            XY xy = queue.poll();
            for(int dir=0;dir<4;dir++){
                int nx = xy.getX() + dx[dir];
                int ny = xy.getY() + dy[dir];
                if(nx<0||nx>=R||ny<0||ny>=C) {
                    System.out.println(disJ[xy.getX()][xy.getY()]+1);
                    return;
                }
                if(checkJ[nx][ny]||board[nx][ny]=='#') continue;
                if(disJ[xy.getX()][xy.getY()] + 1 >= disF[nx][ny] && checkF[nx][ny]) continue;
                disJ[nx][ny] = disJ[xy.getX()][xy.getY()] + 1;
                checkJ[nx][ny] = true;
                queue.add(new XY(nx,ny));
            }
        }
        System.out.println("IMPOSSIBLE");

        /*Arrays.stream(disF).forEach(ints -> {
            Arrays.stream(ints).forEach(System.out::print);
            System.out.println();
        });

        System.out.println();
        Arrays.stream(disJ).forEach(ints -> {
            Arrays.stream(ints).forEach(System.out::print);
            System.out.println();
        });*/
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