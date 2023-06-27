package 동적계획법.신나는함수실행;

//st-lab.tistory.com/190 참고함.
import java.util.Scanner;

public class Main {
    static int dp[][][] = new int[21][21][21];

    public static void main(String[] args) {
        int a,b,c;
        Scanner sc = new Scanner(System.in);

        while(true){
            a = sc.nextInt(); b = sc.nextInt(); c = sc.nextInt();
            if(a==-1&&b==-1&&c==-1) break;
            System.out.printf("w(%d, %d, %d) = %d\n", a, b, c, w(a,b,c));
        }
    }
    public static int w(int a, int b, int c){
        if(a<=0||b<=0||c<=0) return 1;
        else if(a>20 || b>20 || c>20 ) return dp[20][20][20] = w(20,20,20);
        if(dp[a][b][c] != 0) return dp[a][b][c];
        else if(a<b&&b<c) return dp[a][b][c] = w(a,b,c-1) + w(a,b-1,c-1) - w(a,b-1,c);
        else return dp[a][b][c] = w(a-1,b,c) + w(a-1,b-1,c) + w(a-1,b,c-1) - w(a-1,b-1,c-1);
    }
}