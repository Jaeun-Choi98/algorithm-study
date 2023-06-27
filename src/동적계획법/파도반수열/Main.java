package 동적계획법.파도반수열;

import java.util.Scanner;

public class Main {
    static long[] dpArr = new long[101];

    public static void main(String[] args) {
        int T;
        int[] arr;
        long result;
        Scanner scanner = new Scanner(System.in);

        T = scanner.nextInt();
        arr = new int[T];

        for(int i=0;i<T;i++){
            arr[i] = scanner.nextInt();
        }

        init();
        for(int i:arr){
            result = p(i);
            System.out.println(result);
        }
    }

    public static long p(int n){
        if(dpArr[n] != 0) return dpArr[n];
        return dpArr[n] = p(n-1) + p(n-5);

    }

    public static void init(){
        dpArr[1] = 1; dpArr[2] = 1; dpArr[3] =1;
        dpArr[4] = 2; dpArr[5] = 2;
    }
}
