package 분할정복.하노이탑;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n;
        Hanoi hanoi = new Hanoi();
        n = new Scanner(System.in).nextInt();
        hanoi.run(n,1,3,2);
        System.out.println(hanoi.toString());
    }
}

class Hanoi{
    StringBuilder sb;
    int count;

    public Hanoi(){
        sb = new StringBuilder();
        count =0;
    }

    public void run(int n, int a, int c, int b){
        if(n==0) return;
        run(n-1,a,b,c);
        sb.append(a); sb.append(' '); sb.append(c);
        sb.append('\n');
        count++;
        run(n-1,b,c,a);
    }

    @Override
    public String toString() {
        return count + "\n" + sb.toString();
    }
}