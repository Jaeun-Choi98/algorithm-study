package 재귀.fibonacci;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int ans;
        int n;

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        ans = Fibonacci.run(n);
        System.out.println(ans);
    }
}

class Fibonacci{

    public static int run(int n){
        if(n==0||n==1) return n;
        return run(n-2)+run(n-1);
    }
}
