package 동적계획법.타일링;

import java.util.Scanner;

public class Main {

    static int[] d = new int[1001];

    public static void main(String[] args) {

        int N;
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        d[1] = 1;
        d[2] = 2;

        for(int i=3;i<N+1;i++){
            d[i] = (d[i - 1] + d[i - 2])%10007;
        }

        System.out.println(d[N]);
    }
}
