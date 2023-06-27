package DFS와BFS.숨바꼭질;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int boardSize = 100001;
    public static void main(String[] args) {
        int N, K;
        int[] board = new int[boardSize];
        int[] dx = {1,-1,2};

        Scanner scanner = new Scanner(System.in);
        Queue<Integer> queue = new LinkedList<>();

        N = scanner.nextInt(); K = scanner.nextInt();
        if(N==K) {
            System.out.println(0);
            return;
        }
        board[K] = -1;

        queue.add(N);
        while (!queue.isEmpty()){
            int x = queue.poll();
            for(int dir=0;dir<3;dir++){
                int nx;
                if(dir == 2) nx = x*dx[dir];
                else nx = x + dx[dir];

                if(nx<0||nx>=boardSize) continue;
                if(board[nx] == -1){
                    board[nx] = board[x] + 1;
                    System.out.println(board[nx]);
                    return;
                }
                if(board[nx]!=0||nx==N) continue;
                board[nx] = board[x] + 1;
                queue.add(nx);
                /*Arrays.stream(board).forEach(System.out::print);
                System.out.println();*/
            }
        }
    }
}
