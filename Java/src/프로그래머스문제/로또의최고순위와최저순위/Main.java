package 프로그래머스문제.로또의최고순위와최저순위;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};
        Arrays.stream(new Solution().solution(lottos, win_nums)).forEach(System.out::println);
    }
}

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        ArrayList<Integer> rankList = new ArrayList<>();

        // index=당첨갯수, element=순위
        rankList.add(0,6);
        for(int i=1,j=6;i<7;i++){
            rankList.add(i,j--);
        }

        // rankList.stream().forEach(System.out::println);

        int equalCount = 0;
        int zeroCount = 0;

        for(int i=0;i<lottos.length;i++){
            if(Arrays.stream(win_nums).boxed().collect(Collectors.toList()).contains(lottos[i])) equalCount++;
            if(lottos[i] == 0) zeroCount++;
        }

        answer[0] = rankList.get(equalCount + zeroCount);
        answer[1] = rankList.get(equalCount);
        return answer;
    }
}
