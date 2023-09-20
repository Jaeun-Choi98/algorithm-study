package 분할정복.factorial;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int ans;
        int n=-1;

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        ans = new Factorial().run(n);
        System.out.println(ans);
    }
}

class Factorial{
    int sum = 1;
    public int run(int n){
        if(n==1 ||n==0){
            return sum;
        }
        sum*=n;
        return run(n-1);
    }
}


