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
        NQueen nQueen=new NQueen(N);
        nQueen.run(board,0);
        nQueen.printCount();
    }
}

class NQueen {
    int count;
    int N;

    public NQueen(int N) {
        this.count = 0;
        this.N = N;
    }

    public void run(int[][] board,  int currentRow) {

        //탈출조건
        if (N == currentRow) {
            count++;
            return;
        }

        for(int i=0;i<N;i++){
            board[currentRow][i] = 1;
            //검사
            if(inspect(board,currentRow,i)) run(board,currentRow+1);
            board[currentRow][i] = 0;
        }

    }

    private boolean inspect(int[][] board, int currentRow, int currentCol){
        for(int i=0;i<N;i++){
            if((board[i][currentCol] == 1 && i!=currentRow) || (board[currentRow][i] ==1 && i!=currentCol))
                return false;
        }
        for(int i=1;i<N;i++){
            if(currentRow-i>=0 && currentCol-i>=0){
                if(board[currentRow-i][currentCol-i] ==1) return false;
            }
            if(currentRow-i>=0 && currentCol+i<N){
                if(board[currentRow-i][currentCol+i] ==1) return false;
            }
            if(currentRow+i<N && currentCol-i>=0){
                if(board[currentRow+i][currentCol-i] ==1) return false;
            }
            if(currentRow+i<N && currentCol+i<N){
                if(board[currentRow+i][currentCol+i] ==1) return false;
            }
        }
        return true;
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