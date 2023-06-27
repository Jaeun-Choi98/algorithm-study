package 백트래킹.스도쿠;

import java.util.Scanner;


//https://st-lab.tistory.com/119
//정답지 보고 푼 내용..

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] sdkBoard = new int[9][9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++)
                sdkBoard[i][j] = sc.nextInt();
        }
        new Sdk(sdkBoard).run(0,0);
    }
}

class Sdk{

    StringBuilder stringBuilder;
    int[][] sdkBoard;

    public Sdk(int[][] sdkBoard){
        stringBuilder = new StringBuilder();
        this.sdkBoard = sdkBoard;
    }

    public void run(int currentRow, int currentCol) {

        if (currentRow == 9) {
            printBoard();
            System.exit(0);
        }

        if (currentCol == 9) {
            run(currentRow + 1, 0);
            return;
        }

        if (sdkBoard[currentRow][currentCol] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (inspect(currentRow, currentCol, i)) {
                    sdkBoard[currentRow][currentCol] = i;
                    run(currentRow, currentCol + 1);
                    sdkBoard[currentRow][currentCol] = 0;
                }
            }
            if (sdkBoard[currentRow][currentCol] == 0) return;
        }
        run(currentRow, currentCol + 1);
    }

    private boolean inspect(int currentRow, int currentCol, int currentValue){

        for(int i=0;i<9;i++){
            if(sdkBoard[i][currentCol] == currentValue) return false;
        }
        for(int i=0;i<9;i++){
            if(sdkBoard[currentRow][i] == currentValue) return false;
        }

        int startRow = currentRow/3*3;
        int startCol = currentCol/3*3;

        for(int i=startRow;i<startRow+3;i++){
            for(int j=startCol;j<startCol+3;j++){
                if(sdkBoard[i][j]==currentValue) return false;
            }
        }
        return true;
    }

    private void printBoard(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                stringBuilder.append(sdkBoard[i][j]).append(' ');
            }
            stringBuilder.append('\n');
        }
        System.out.println(stringBuilder);
    }
}