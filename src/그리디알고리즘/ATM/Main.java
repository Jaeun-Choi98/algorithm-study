package 그리디알고리즘.ATM;

import java.util.Arrays;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        int N;
        int[] arr;
        int sum=0;
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N];

        for(int i=0;i<N;i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        int buf=0;
        for(int i:arr){
            buf += i;
            sum += buf;
        }

        System.out.println(sum);
    }
}
