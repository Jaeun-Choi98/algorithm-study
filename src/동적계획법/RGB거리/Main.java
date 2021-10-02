package 동적계획법.RGB거리;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        int[][] dpArr;
        int[] resultArr;
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        dpArr = new int[N+1][3];
        resultArr = new int[N];

        for(int i=1;i<N+1;i++){
            for(int j=0;j<3;j++)
                dpArr[i][j] = scanner.nextInt();
        }

        for (int i=2;i<N+1;i++){
            dpArr[i][0] += Math.min(dpArr[i-1][1],dpArr[i-1][2]);
            dpArr[i][1] += Math.min(dpArr[i-1][0],dpArr[i-1][2]);
            dpArr[i][2] += Math.min(dpArr[i-1][1],dpArr[i-1][0]);
        }

        System.out.println(Math.min(Math.min(dpArr[N][0],dpArr[N][1]),dpArr[N][2]));
    }
}
