package 분할정복.별찍기;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n;
        n = new Scanner(System.in).nextInt();
        Star star = new Star(n);
        star.init();
        star.run(n);
    }
}

class Star{
    int n;
    char[][] chArray;

    public Star(int n){
        this.n = n;
        chArray = new char[n][n];
    }

    public void init(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
                chArray[i][j] = '*';
        }
    }

    public void run(int N){
        int n = N/3;
        if(n==0) {
            System.out.println(toString());
            return;
        }
        for(int k=0;k<this.n/N;k++){
            for(int l=0;l<this.n/N;l++){
                for(int i=0;i<n;i++){
                    for(int j=0;j<n;j++){
                        chArray[n+i+(k*N)][n+j+(l*N)] = ' ';
                    }
                }
            }
        }
        run(n);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++) {
            sb.append(chArray[i]);
            sb.append('\n');
        }
        return sb.toString();
    }
}