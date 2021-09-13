package 백트래킹.nqueen;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        int[][] board;

        N=new Scanner(System.in).nextInt();
        board = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++) {
                board[i][j] = 0;
            }
        }
        NQueen nQueen=new NQueen();
        nQueen.run(board,N,0);
        nQueen.printCount();
    }
}

class NQueen {
    int count;

    public NQueen() {
        this.count = 0;
    }

    public void run(int[][] board, int N, int currentRow) {

        //탈출조건
        if (N == currentRow) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            board[currentRow][i] = 1;

            for (int j = 0; j < N; j++) {
                printBoard(board,N);
                System.out.println();

                if (board[j][i] == 1 && currentRow!=j) {
                    board[currentRow][i] = 0;
                    break;
                }
                if(board[currentRow][j]==1&& i!=j){
                    board[currentRow][i] = 0;
                    break;
                }

                if (currentRow - j  >= 0 && i - j  >= 0) {
                    if (board[currentRow - j][i - j] == 1) {
                        board[currentRow][i] = 0;
                        break;
                    }
                }
                if (currentRow + j <= N - 1 && i + j <= N-1) {
                    if (board[currentRow + j][i + j] == 1) {
                        board[currentRow][i] = 0;
                        break;
                    }
                }
                if (currentRow - j >= 0 && i + j <= N - 1) {
                    if (board[currentRow - j][i + j] == 1) {
                        board[currentRow][i] = 0;
                        break;
                    }
                }
                if (currentRow + j <= N - 1 && i - j  >= 0) {
                    if (board[currentRow + j][i - j] == 1) {
                        board[currentRow][i] = 0;
                        break;
                    }
                }
            }
            if (board[currentRow][i] == 1) {
                run(board, N, currentRow + 1);
            }
        }

    }
    public void printCount(){
        System.out.println(count);
    }

    public void printBoard(int[][] board, int N){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(board[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}