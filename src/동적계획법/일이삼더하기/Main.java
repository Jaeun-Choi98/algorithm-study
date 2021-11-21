package 동적계획법.일이삼더하기;

import java.util.Scanner;

public class Main {

    static int[] d = new int[11];

    public static void main(String[] args) {
        int T;
        Scanner scanner = new Scanner(System.in);

        d[1] = 1; d[2] = 2; d[3] = 4;
        T = scanner.nextInt();
        for(int i=0;i<T;i++){
            int buf = scanner.nextInt();
            if(d[buf] == 0){
                for(int j=4;j<=buf;j++){
                    d[j] = d[j-3] + d[j-2] + d[j-1];
                }
            }
            System.out.println(d[buf]);
        }
    }
}
