package bruteforce.블랙잭;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int cardNum, M;
        int[] cardList;

        Scanner sc = new Scanner(System.in);

        cardNum = sc.nextInt();
        cardList = new int[cardNum];

        for(int i=0;i<cardNum;i++) cardList[i] = sc.nextInt();

        BlackJac blackJac = new BlackJac();
        blackJac.run(cardList);
        System.out.println(blackJac.toString());
    }
}

class BlackJac{
    int[] answer;

    public BlackJac(){
        answer = new int[3];
        for(int i=0;i<3;i++) answer[i] = 1;
    }

    public void run(int[] cardList){

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<3;i++){
            sb.append(answer[i]); sb.append('\n');
        }
        return sb.toString();
    }
}
