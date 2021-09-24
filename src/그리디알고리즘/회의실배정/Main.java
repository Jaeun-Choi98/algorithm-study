package 그리디알고리즘.회의실배정;

import java.util.Arrays;
import java.util.Scanner;

//활동선택문제
public class Main {
    public static void main(String[] args) {
        int N;
        Meeting[] meetings;
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        meetings = new Meeting[N];
        for (int i = 0; i < N; i++) {
            meetings[i] = new Meeting(sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(meetings,(o1, o2) ->
        {
            if(o1.getEnd() == o2.getEnd()) return o1.getStart() - o2.getStart();
            else return o1.getEnd() - o2.getEnd();
        });

        int count =0;
        int check =0;
        for(int i=0;i<N;i++){
            if(check <= meetings[i].getStart()){
                check = meetings[i].getEnd();
                count++;
            }
        }

        System.out.println(count);
    }
}

class Meeting{
    int start, end;

    public Meeting(int s, int e) {
        this.start = s;
        this.end = e;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}