package 백트래킹.스타트와링크;

import java.util.Scanner;


//BruteForce(BitMask)
//[probelm](https://www.acmicpc.net/problem/14889)

//과거 시간초과가 나서 해결하지 못했던 문제 BitMask를 활용해서 다시 풀어보았다..;;
public class Main {

    static int[][] grap;
    static int result, N;
    public static void main(String[] args) {
        int[][] board;
        Scanner sc = new Scanner(System.in);


        N = sc.nextInt();
        board = new int[N][N];
        grap = new int[N][N];
        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = sc.nextInt();
                grap[i][j] = board[i][j];
            }
        }
        result = Integer.MAX_VALUE;
        search(0,0,0);
        System.out.println(result);

        /*
        시간초과
        StartLink startLink = new StartLink(board,N);
        startLink.run(0);
        startLink.print();
        */
    }
    public static void search(int cnt, int bitMask,int isHalf){
        if(cnt == N){
            if(isHalf != N/2) return;
            int asum =0;
            int bsum =0;
            for(int i=0;i<N;i++){
                if((bitMask&(1<<i))>0){
                    for(int j=i+1;j<N;j++){
                        if((bitMask&(1<<j))>0){
                            asum+=grap[i][j]+grap[j][i];
                        }
                    }
                }else{
                    for(int j=i+1;j<N;j++){
                        if((bitMask&(1<<j))==0){
                            bsum+=grap[i][j]+grap[j][i];
                        }
                    }
                }
            }
            result = Math.min(result,Math.abs(asum-bsum));
            return;
        }

        search(cnt+1,bitMask|(1<<cnt),isHalf+1);
        search(cnt+1,bitMask|(0<<cnt),isHalf);
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
            if(check[i]) continue;
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
