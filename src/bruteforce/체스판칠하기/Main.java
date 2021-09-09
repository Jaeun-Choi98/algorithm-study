package bruteforce.체스판칠하기;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N,M;
        String[] board;
        Scanner sc = new Scanner(System.in);

        N=sc.nextInt(); M=sc.nextInt();
        sc.nextLine();
        board = new String[N];
        for(int i=0;i<N;i++) board[i] = sc.nextLine();

        new Chess(N,M,board).run();
    }
}

class Chess{
    int N,M;
    int count,countbuf;
    char[][] board;


    public Chess(int N, int M, String[] board){
        this.N = N; this.M = M;
        this.board = new char[N][M];

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                this.board[i][j] = board[i].charAt(j);
            }
        }

        countbuf = 0;
        count = 9999999;
    }

    public void run(){
        char[][] copyBoard = new char[8][8];
        char ch;

        for(int i=0;i<N;i++){
            if(i+8>N) break;
            for(int j=0;j<M;j++){
                if(j+8>M) break;

                //8*8체스판 샘플링
                for(int k=0;k<8;k++){
                    for(int l=0;l<8;l++){
                        copyBoard[k][l] = board[i+k][j+l];
                    }
                }

                for(int k=0;k<7;k++){
                    for(int l=0;l<7;l++){
                        if(copyBoard[k][l] == copyBoard[k][l+1]) {
                            countbuf++;
                            if(copyBoard[k][l+1]=='B') copyBoard[k][l+1] = 'W';
                            else copyBoard[k][l+1] = 'B';
                        }
                        if(copyBoard[k][l] == copyBoard[k+1][l]){
                            countbuf++;
                            if(copyBoard[k+1][l]=='B') copyBoard[k+1][l] = 'W';
                            else copyBoard[k+1][l] = 'B';
                        }
                    }
                }
                if(count > countbuf) count = countbuf;
                countbuf =0;

                //첫문자 바꿔서 돌리기
                //리셋
                for(int k=0;k<8;k++){
                    for(int l=0;l<8;l++){
                        copyBoard[k][l] = board[i+k][j+l];
                    }
                }
                ch = copyBoard[i][j];
                if(ch=='W') copyBoard[i][j] ='B';
                else copyBoard[i][j] = 'W';
                countbuf++;

                for(int k=0;k<7;k++){
                    for(int l=0;l<7;l++){
                        if(copyBoard[k][l] == copyBoard[k][l+1]) {
                            countbuf++;
                            if(copyBoard[k][l+1]=='B') copyBoard[k][l+1] = 'W';
                            else copyBoard[k][l+1] = 'B';
                        }
                        if(copyBoard[k][l] == copyBoard[k+1][l]){
                            countbuf++;
                            if(copyBoard[k+1][l]=='B') copyBoard[k+1][l] = 'W';
                            else copyBoard[k+1][l] = 'B';
                        }
                    }
                }
                if(count > countbuf) count = countbuf;
                countbuf =0;
            }
        }
        System.out.println(count);
    }

}
