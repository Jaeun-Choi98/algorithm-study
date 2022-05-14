package 프로그래머스문제.신고결과받기;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k = 2;
        new Solution().solution(id_list,report,k);
    }
}
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        HashMap<String, Integer> repMap = new HashMap<>();
        HashMap<String, Integer> repUser = new HashMap<>();

        HashSet<String> set = new HashSet<String>(Arrays.asList(report));

        for(String str : id_list){
            repUser.put(str,0);
            repMap.put(str,0);
        }

        String[] rp;
        for(String str : set){
            rp = str.split(" ");
            repUser.put(rp[1], repUser.get(rp[1])+1);
        }

        ArrayList<String> stUserList = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : repUser.entrySet()){
            if((Integer)entry.getValue() >= k){
                stUserList.add((String)entry.getKey());
            }
        }

        for(String str : set){
            rp = str.split(" ");
            if(stUserList.contains(rp[1])){
                repMap.put(rp[0],repMap.get(rp[0])+1);
            }
        }

        /*
        for(Map.Entry<String, Integer> entry : repMap.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }*/

        int idx = 0;
        for(String str: id_list){
            answer[idx++] = repMap.get(str);
        }

        Arrays.stream(answer).forEach(System.out::println);
        return answer;
    }
}