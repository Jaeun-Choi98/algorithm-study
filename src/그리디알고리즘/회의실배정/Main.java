package 그리디알고리즘.회의실배정;

import java.util.Arrays;
import java.util.Scanner;

//활동선택문제(Activity Selection problem)
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
        Arrays.stream(meetings).sorted()

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

    public int getSub(){
        return end - start;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}