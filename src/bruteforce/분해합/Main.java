package bruteforce.분해합;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        DecoSum decoSum = new DecoSum();
        decoSum.run(N);
        System.out.println(decoSum.toString());
    }
}

class DecoSum{
    ArrayList<Integer> arrayList;
    StringBuilder sb;

    public DecoSum(){
        arrayList = new ArrayList<Integer>();
        sb = new StringBuilder();
    }

    @Override
    public String toString() {
        if(arrayList.isEmpty()) sb.append(0);
        else{
            Integer integer = arrayList.stream().min(Integer::compareTo).get();
            sb.append(integer.intValue());
        }
        return sb.toString();
    }

    public void run(int N){
        int valid=0;
        int positionNumber=0;
        for(int i=0;i<N;i++){
            valid = i;
            positionNumber = valid;
            while (positionNumber>=10){
                valid += positionNumber%10;
                positionNumber /=10;
            }
            valid += positionNumber;
            if(valid == N) arrayList.add(i);
        }
    }
}
