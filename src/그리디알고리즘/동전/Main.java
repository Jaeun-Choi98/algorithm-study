package 그리디알고리즘.동전;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N,K,intBuf;
        int count =0;
        int[] arr;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); K = sc.nextInt();
        arr = new int[N];

        for(int i=0;i<N;i++){
            intBuf = sc.nextInt();
            arr[i] = intBuf;
        }

        for(int i=N-1;i>=0;i--){
            if(K/arr[i] == 0) continue;
            else{
                count += K/arr[i];
                K %= arr[i];
                if(K==0) break;
            }
        }

        System.out.println(count);
    }

}