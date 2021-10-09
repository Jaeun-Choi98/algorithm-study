package 재귀.곱셈;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Integer a,b,c;
        Scanner scanner = new Scanner(System.in);

        a=scanner.nextInt();b=scanner.nextInt();c=scanner.nextInt();
        System.out.println(mut(a,b,c));
    }

    static public Integer mut(Integer a, Integer b, Integer c){
        if(b==1) return a%c;
        Integer val = mut(a,b/2,c);
        val = val*val%c;
        if(b%2==0) return val;
        return val*a%c;
    }
}
