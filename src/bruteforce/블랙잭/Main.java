package bruteforce.블랙잭;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int cardNum, M;
        int[] cardList;

        Scanner sc = new Scanner(System.in);

        cardNum = sc.nextInt(); M = sc.nextInt();
        cardList = new int[cardNum];

        for(int i=0;i<cardNum;i++) cardList[i] = sc.nextInt();

        BlackJac blackJac = new BlackJac();
        blackJac.run(cardList, M);
        System.out.println(blackJac.toString());
    }
}

class BlackJac{
    Integer maxSum;

    public BlackJac(){
        maxSum = 9;
    }

    public void run(int[] cardList, int M){
        for(int i=0;i<cardList.length;i++){
            for(int j=i+1;j<cardList.length;j++){
                for(int k =j+1;k<cardList.length;k++){
                    if(sum(cardList,i,j,k)<=M){
                        if(maxSum < sum(cardList,i,j,k)) maxSum = sum(cardList, i, j, k);
                    }
                }
            }
        }
    }

    private int sum(int[] charArr, int a, int b, int c){
        int sum =0;
        sum = charArr[a] + charArr[b] + charArr[c];
        return sum;
    }

    @Override
    public String toString() {
        return maxSum.toString();
    }
}
