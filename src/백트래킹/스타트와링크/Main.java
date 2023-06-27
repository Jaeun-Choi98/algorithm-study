package 백트래킹.스타트와링크;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        int[][] board;
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        board = new int[N][N];
        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        StartLink startLink = new StartLink(board,N);
        startLink.run(0);
        startLink.print();
    }
}

class StartLink{
    StringBuilder stringBuilder;
    int[][] board;
    boolean[] check;
    int N, bufSub, sub;

    public StartLink(int[][] board,int N){
        stringBuilder = new StringBuilder();
        this.board = board;
        this.N = N;
        check = new boolean[N];
        this.bufSub=0; this.sub=99999999;
    }

    public void run(int teamNum){
        if(teamNum == N/2){
            calc();
            if(bufSub<sub) sub = bufSub;
            return;
        }

        for(int i=teamNum;i<N;i++){
            check[i] = true;
            run(teamNum+1);
            check[i] = false;
        }
        return;
    }

    private void calc(){
        int aScore, bScore;
        aScore=0;bScore=0;

        for(int i=0;i<N;i++){
            if(check[i] == true){
                for(int j=i+1;j<N;j++){
                    if(check[j] == true){
                        aScore += board[i][j];
                        aScore += board[j][i];
                    }
                }
            }
        }

        for(int i=0;i<N;i++){
            if(check[i] == false){
                for(int j=i+1;j<N;j++){
                    if(check[j] == false){
                        bScore += board[i][j];
                        bScore += board[j][i];
                    }
                }
            }
        }
        this.bufSub = Math.abs(aScore-bScore);
    }

    public void print(){
        System.out.println(sub);
    }
}
